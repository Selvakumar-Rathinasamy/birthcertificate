package com.obc.obc.Controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.obc.obc.Entity.Application;
import com.obc.obc.Service.AdminService;
import com.obc.obc.Service.ApplicationService;
// import com.obc.obc.Service.CustomUserDetailsService;
import com.obc.obc.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@SessionAttributes({ "loggedInUser", "loggedInAdmin" })
public class ObcController {
    private final UserService userService;
    private final AdminService adminService;
    private final ApplicationService applicationService;
    private static final Logger logger = LoggerFactory.getLogger(ObcController.class);
    public ObcController(UserService userService, AdminService adminService, ApplicationService applicationService) {
        this.userService = userService;
        this.adminService = adminService;
        this.applicationService = applicationService;
    }

    @RequestMapping("/Index")
    public String Index() {
        return "Index";
    }

    @RequestMapping("/Userpage")
    public String Userpage() {
        return "Userpage";
    }

    @RequestMapping("/Createaccount")
    public String Createaccount() {
        return "Createaccount";
    }

    @RequestMapping("/Verify")
    public String Verify() {
        return "Verify";
    }

    @RequestMapping("/Adminlogin")
    public String Adminlogin() {
        return "Adminlogin";
    }

    @RequestMapping("/Certificate")
    public String Certificate() {
        return "Certificate";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String moblie,
            @RequestParam String address, @RequestParam String password, LocalDate regdate, String role,
            HttpSession session, RedirectAttributes redirectAttributes) {
                
        try {
            System.out.println(firstname + lastname + moblie + address + password + regdate.now() + role);
            userService.savUser(firstname, lastname, moblie, address, password, regdate.now(), role);
            session.setAttribute("AccountCreate", firstname);
            return "Userpage"; // Assuming "createSuccess" is a success view
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Account creation failed: " + e.getMessage());
            return "redirect:/Createaccount"; // Redirect to the account creation page with an error message
        }
    }

    // User Login Command
    // @PostMapping("/login")
    // public String login(@RequestParam String firstname, @RequestParam String password, HttpSession session,PasswordEncoder passwordEncoder) {
    //     logger.info("get from url: {}", firstname);
    //     boolean isvalidUser = userService.ValidateLogin(firstname, password);
    //     if (isvalidUser) {
    //         logger.info("Login successful for user: {}", firstname);
    //         session.setAttribute("loggedInUser", firstname);
    //         return "redirect:/userdashboard";
    //     } else {
    //         logger.warn("Login successful for user: {}", firstname);
    //         return "redirect:/Userpage";
    //     }
    // }

    @Autowired
	UserDetailsService userDetailsService;
   
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/adminlogin")
    public String adminlogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        boolean isvalidAdmin = adminService.ValidateAdmin(username, password);
        if (isvalidAdmin) {
            session.setAttribute("loggedInAdmin", username);
            return "redirect:/Admindashboard";
        } else {
            return "redirect:/Adminlogin";
        }
    }

   

    // Show Command
    @GetMapping("/userdashboard")
    public String getAllApplications(Model model, HttpSession session,Principal principal) {
        session.setAttribute("Showing", model);
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        List<Application> applications = applicationService.getAllApplications();
        model.addAttribute("applications", applications);
        return "Userdashboard";
    }

    // Show and Count Command
    @RequestMapping("/Admindashboard")
    public String getApplicationsByStatus(Model model, HttpSession session,Principal principal) {
        session.setAttribute("ShowandCount", model);
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
        // Fetch pending applications
        List<Application> pendingApplications = applicationService.getApplicationsByStatus("pending");
        model.addAttribute("pendingApplications", pendingApplications);
        long pendingApplicationsCount = applicationService.countApplications("pending");
        model.addAttribute("pendingApplicationsCount", pendingApplicationsCount);

        // Fetch approved applications
        List<Application> approvedApplications = applicationService.getApplicationsByStatus("approve");
        model.addAttribute("approvedApplications", approvedApplications);
        long approvedApplicationsCount = applicationService.countApplications("approve");
        model.addAttribute("approvedApplicationsCount", approvedApplicationsCount);

        // Fetch rejected applications
        List<Application> rejectedApplications = applicationService.getApplicationsByStatus("reject");
        model.addAttribute("rejectedApplications", rejectedApplications);
        long rejectedApplicationsCount = applicationService.countApplications("reject");
        model.addAttribute("rejectedApplicationsCount", rejectedApplicationsCount);

        // Fetch all applications
        List<Application> applications = applicationService.getAllApplication();
        model.addAttribute("applications", applications);
        long totalApplications = applicationService.countApplications();
        model.addAttribute("totalApplications", totalApplications);
        return "Admindashboard";
    }

    // Form Applying
    @PostMapping("/Apply")
    public String Apply(@RequestParam String userid, @RequestParam String applicationid, @RequestParam String name,
            @RequestParam String DoB, @RequestParam String gender, @RequestParam String birthplace,
            @RequestParam String fathername, @RequestParam String mothername, @RequestParam String permanetadd,
            @RequestParam String postaladd, @RequestParam String mobno, @RequestParam String email,
            LocalDate applyieddate, @RequestParam String status, HttpSession session) {
        session.setAttribute("Applying", userid);
        applicationService.saveApplication(userid, applicationid, name, DoB, gender, birthplace, fathername, mothername,
                permanetadd, postaladd, mobno, email, applyieddate, status);
        return "redirect:/userdashboard";
    }

    // View Command
    @RequestMapping("/View/{id}")
    public String viewApplication(@PathVariable Long id, Model m, HttpSession session) {
        session.setAttribute("Viewing", id);
        m.addAttribute("applications", applicationService.viewApplication(id));
        return "View";
    }

    // Edit Command
    @RequestMapping("/userdashboard/Edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Application applications = applicationService.viewApplication(id);
        model.addAttribute("applications", applications);
        return "Edit";
    }

    @PostMapping("/editApplication")
    public String editApplication(@ModelAttribute Application application) {
        applicationService.updateApplication(application);
        return "redirect:/userdashboard";
    }

    // Delete Command
    @GetMapping("/Admindashboard/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return "redirect:/Admindashboard";
    }

    // Status Update
    @PatchMapping("/{id}/status")
    public String updateBcStatus(@PathVariable Long id, @RequestParam String status) {
        applicationService.viewApplication(id);
        return "redirect:/Admindashboard";
    }

    @GetMapping("/status")
    public String viewStatus(Model model) {
        List<Application> applications = applicationService.getAllApplication();
        model.addAttribute("applications", applications);
        return "redirect:/Admindashboard";
    }

    @PostMapping("/Admindashboard/applications/update-status")
    public String updateBcs(@RequestParam Long id, @RequestParam String status) {
        applicationService.setBcStatus(id, status);
        return "redirect:/Admindashboard";
    }

    // Logout Commant
    @GetMapping("/logoutUser")
    public String logoutUser(HttpServletRequest request, RedirectAttributes ra) {
        request.getSession().invalidate();
        ra.addFlashAttribute("LogoutSucsess", "You have been successfully logged out.");
        return "redirect:/Userpage";
    }

    @GetMapping("/logoutAdmin")
    public String logoutAdmin(HttpServletRequest request, RedirectAttributes ra) {
        request.getSession().invalidate();
        ra.addFlashAttribute("LogoutSucsess", "You have been successfully logged out.");
        return "redirect:/Adminlogin";
    }

    // Certificate Verification
    @GetMapping("/Verify/{mobno}/{applicationid}")
    public String getDetails(@RequestParam String mobno, @RequestParam String applicationid, Model model,
            HttpSession session) {
        System.out.println(mobno + " " + applicationid);
        session.setAttribute("certificateVerify", applicationid);
        String status = applicationService.getStatus(mobno, applicationid);

        Application application = applicationService.getByCertificate(mobno, applicationid);
        model.addAttribute("certificateDetails", application);
        System.out.println("Application " + application);

        System.out.println(status);
        if (status.equals("approve")) {
            return "/Certificate";
        } else if (status.equals("reject")) {
            return "ApplicationRejection";
        } else {
            return "Detailspending";
        }
    }

    // Search Engine
    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model, HttpSession session) {
        session.setAttribute("Search", query);
        List<Application> result = applicationService.search(query);
        model.addAttribute("result", result);
        return "SearchResult";
    }

    // Between Date to search
    @GetMapping("/betweenDate")
    public String betweenDate(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
            Model model) {
        model.addAttribute("betweenDate", applicationService.getApplicationBetweenDates(startDate, endDate));
        return "BetweenDateSearch";
    }
}