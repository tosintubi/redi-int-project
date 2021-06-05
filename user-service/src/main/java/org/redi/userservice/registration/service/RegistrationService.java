package org.redi.userservice.registration.service;

import lombok.AllArgsConstructor;
import org.redi.userservice.appuser.model.AppUser;
import org.redi.userservice.appuser.model.AppUserRole;
import org.redi.userservice.appuser.service.AppUserService;
import org.redi.userservice.email.EmailSender;
import org.redi.userservice.email.EmailService;
import org.redi.userservice.registration.model.RegistrationRequest;
import org.redi.userservice.registration.token.ConfirmationToken;
import org.redi.userservice.registration.token.ConfirmationTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidatorService emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailService;
    //private final EmailTemplateService emailTemplateService;
    //
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException(String.format("%s email is not in the valid format"));
        }

        String token = appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER // Assumes USER
        ));

        // TODO: Replace with Service URL
        String confirmationLink = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailService.sendRegistrationEmail(
                request.getEmail(),
                buildEmail(request.getEmail(), confirmationLink));
        return token;
    }

    @Transactional
    public String confirmToken(String token){
        // Checks for token
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token not found")
        );

        // Token exists
        // Confirmation already happened because there's an entry confirmed at.
        if (confirmationToken.isConfirmed()){
            throw new IllegalStateException("Email has already been confirmed");
        }

        // Time has passed and token has expired
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token has already expired");
        }

        // Token exists and is still valid
        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

        return "confirmed";
    }

    public String buildEmail(String email, String link){
        return "<div class=\"es-wrapper-color\" style=\"background-color:#FFFFFF\"> \n" +
                "   <!--[if gte mso 9]>\n" +
                "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "\t\t\t\t<v:fill type=\"tile\" color=\"#ffffff\"></v:fill>\n" +
                "\t\t\t</v:background>\n" +
                "\t\t<![endif]--> \n" +
                "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#FFFFFF\"> \n" +
                "     <tr> \n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0\"> \n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"> \n" +
                "         <tr> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\"> \n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:700px\"> \n" +
                "             <tr> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-bottom:10px;padding-top:20px;padding-left:20px;padding-right:20px\"> \n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                 <tr> \n" +
                "                  <td class=\"es-m-p0r\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:660px\"> \n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                     <tr> \n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px;font-size:0\"> \n" +
                "                       <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                         <tr> \n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #CCCCCC;background:none;height:1px;width:100%;margin:0px\"></td> \n" +
                "                         </tr> \n" +
                "                       </table></td> \n" +
                "                     </tr> \n" +
                "                     <tr> \n" +
                "                      <td style=\"padding:0;Margin:0\"> \n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"es-menu\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                         <tr class=\"links\"> \n" +
                "                          <td align=\"center\" valign=\"top\" width=\"100%\" id=\"esd-menu-id-2\" style=\"Margin:0;padding-left:5px;padding-right:5px;padding-top:10px;padding-bottom:10px;border:0\"><a target=\"_blank\" href=\"https://redi-books.org\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;display:block;color:#333333;font-size:14px\">ReDi Books</a></td> \n" +
                "                         </tr> \n" +
                "                       </table></td> \n" +
                "                     </tr> \n" +
                "                     <tr> \n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px;font-size:0\"> \n" +
                "                       <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                         <tr> \n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #CCCCCC;background:none;height:1px;width:100%;margin:0px\"></td> \n" +
                "                         </tr> \n" +
                "                       </table></td> \n" +
                "                     </tr> \n" +
                "                   </table></td> \n" +
                "                 </tr> \n" +
                "               </table></td> \n" +
                "             </tr> \n" +
                "           </table></td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"> \n" +
                "         <tr> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\"> \n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:700px\"> \n" +
                "             <tr> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-bottom:20px;padding-left:20px;padding-right:20px;padding-top:40px\"> \n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                 <tr> \n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:660px\"> \n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                     <tr> \n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img src=\"https://rprnym.stripocdn.email/content/guids/CABINET_2663efe83689b9bda1312f85374f56d2/images/10381620386430630.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"100\"></td> \n" +
                "                     </tr> \n" +
                "                     <tr> \n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0\"><h2 style=\"Margin:0;line-height:43px;mso-line-height-rule:exactly;font-family:Montserrat, sans-serif;font-size:36px;font-style:normal;font-weight:normal;color:#333333\">Verify your email to finish signing up</h2></td> \n" +
                "                     </tr> \n" +
                "                     <tr> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px;font-size:0\"> \n" +
                "                       <table border=\"0\" width=\"40%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:40% !important;display:inline-table\" role=\"presentation\"> \n" +
                "                         <tr> \n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #CCCCCC;background:none;height:1px;width:100%;margin:0px\"></td> \n" +
                "                         </tr> \n" +
                "                       </table></td> \n" +
                "                     </tr> \n" +
                "                     <tr> \n" +
                "                      <td align=\"center\" class=\"es-m-p0r\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px;padding-right:40px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Montserrat, sans-serif;line-height:24px;color:#333333;font-size:16px\">WELCOME TO REDI-BOOKS</p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Montserrat, sans-serif;line-height:24px;color:#333333;font-size:16px\"><br></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:Montserrat, sans-serif;line-height:24px;color:#333333;font-size:16px\">Please confirm that <strong><a target=\"_blank\" href=\"mailto:"+email+"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#134F5C;font-size:16px\">"+email+"</a></strong>&nbsp;is your email address by clicking on the button below or use this link <a target=\"_blank\" href=\"" + link +"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#134F5C;font-size:16px;word-break:break-all\">https://lucasandjoseph.com/confirm-email</a> within <strong>48 hours</strong>.<br><br></p></td> \n" +
                "                     </tr> \n" +
                "                     <tr> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px;font-size:0\"> \n" +
                "                       <table border=\"0\" width=\"40%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:40% !important;display:inline-table\" role=\"presentation\"> \n" +
                "                         <tr> \n" +
                "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #CCCCCC;background:none;height:1px;width:100%;margin:0px\"></td> \n" +
                "                         </tr> \n" +
                "                       </table></td> \n" +
                "                     </tr> \n" +
                "                     <tr> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-l\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\"><span class=\"es-button-border\" style=\"border-style:solid;border-color:#999999;background:#FFFFFF;border-width:1px;display:inline-block;border-radius:0px;width:auto\"><a href=\"https://viewstripo.email\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#666666;font-size:16px;border-style:solid;border-color:#FFFFFF;border-width:10px 30px 10px 30px;display:inline-block;background:#FFFFFF;border-radius:0px;font-family:Montserrat, sans-serif;font-weight:normal;font-style:normal;line-height:19px;width:auto;text-align:center\">Verify my email</a></span></td> \n" +
                "                     </tr> \n" +
                "                   </table></td> \n" +
                "                 </tr> \n" +
                "               </table></td> \n" +
                "             </tr> \n" +
                "           </table></td> \n" +
                "         </tr> \n" +
                "       </table></td> \n" +
                "     </tr> \n" +
                "   </table> \n" +
                "  </div>";
    }
}
