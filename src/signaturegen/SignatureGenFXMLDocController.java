/**
 * @author nicktuttle
 * 01-18-2016
 */
package signaturegen;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.image.*;
import javafx.scene.web.*;

public class SignatureGenFXMLDocController implements Initializable {
    private String firstName, lastName, directLine, title, cellPhone, socialM, socialMVT;
    @FXML private TextField txtFirstName, txtLastName, txtDirectLine, txtTitle, txtCellPhone;
    @FXML private CheckBox chkSocialIcons;
    @FXML private Button btnGenerate;
    @FXML private ImageView imgCompanyLogo;
    @FXML private WebView webView;
    @FXML private WebEngine engine;
   
    @FXML private void handleButtonAction(ActionEvent event) throws InterruptedException { 
        firstName = txtFirstName.getText();
        lastName = txtLastName.getText();
        directLine = txtDirectLine.getText();
        title = txtTitle.getText();
        try{   
        if(!nameVerify(firstName))
            throw new Exception("First Name must be entered.");
        else
            firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
        if(!nameVerify(lastName))
            throw new Exception("Last Name must be entered.");
        else
            lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
        if(!phoneVerify(directLine))
            throw new Exception("Phone Required: Must be 555-555-5555 / 555.555.5555");
        if(!nameVerify(title))
            throw new Exception("Title must be entered.");
        else
            title = title.substring(0,1).toUpperCase() + title.substring(1);
        if(txtCellPhone.getText().trim().isEmpty()) //Optional Cell Phone
            cellPhone = "";
        else
            if(!phoneVerify(txtCellPhone.getText()))
                throw new Exception("Cell Phone: Must be 555-555-5555 / 555.555.5555");
            else
                cellPhone = " | Cell: " + txtCellPhone.getText();
        
        if(chkSocialIcons.isSelected()){  //Social Media Icons Under Signature
            socialM = "<p>Follow us on:</p><a href=\"https://twitter.com/userName\"><img src=\"http://twitter-badges.s3.amazonaws.com/t_small-a.png\"></a><a href=\"https://www.youtube.com/user/userName\"><img src=\"http://www.42u.com/images/youtube-icon-22w.jpg\"></a><a href=\"https://www.facebook.com/userName/\"><img src=\"http://www.42u.com/images/facebook-icon-22w.jpg\"></a>\n";
            socialMVT = "<a href=\"https://twitter.com/userName\" target=\"_blank\"><span style=\"text-decoration: none; text-underline: none\"><img id=\"_x0000_i1026\" alt=\"Twitter\" border=\"0\" class=\"\" height=\"24\" src=\"https://ci6.googleusercontent.com/proxy/Ezdy0NZrR0lxd60-3Chht-IaB_gi44lX8E_LZbBz-75aEQnjjX7N4b2bYs7cxfpl3az6VZRtpD-W6BNhFnlv-V9jp9D1CaKYSupvLoZ7=s0-d-e1-ft#https://s3.amazonaws.com/htmlsig-assets/grey/twitter.png\" style=\"border-bottom-style: none; border-left-style: none; border-right-style: none; border-top-style: none; display: inline; margin-bottom: 2px\" width=\"24\"></span></a><img id=\"_x0000_i1027\" border=\"0\" class=\"\" src=\"https://ci6.googleusercontent.com/proxy/wu_nasMQvgujkWqFw0VMsW-Du2jSo6681tevUA0WRlUscqtAYCqt46KjsfRnLT8nNNuyZ9gHcUSlvXRbY0U2Ki4BGp5uzmgE=s0-d-e1-ft#https://s3.amazonaws.com/htmlsig-assets/spacer.gif\" width=\"2\">&nbsp;<a href=\"https://www.facebook.com/userName\" target=\"_blank\"><span style=\"text-decoration: none; text-underline: none\"><img id=\"_x0000_i1028\" alt=\"Facebook\" border=\"0\" class=\"\" height=\"24\" src=\"https://ci6.googleusercontent.com/proxy/XfuWLnfAA4VRPR9UGkgRFFqD6gPEkqOOyGc4xl-NAO10W5hXL3eb0574QpkbdgOXpo_9DKwcc41xYBqshtrDB8e_dki8RBRoDEAVSuIkdQ=s0-d-e1-ft#https://s3.amazonaws.com/htmlsig-assets/grey/facebook.png\" style=\"border-bottom-style: none; border-left-style: none; border-right-style: none; border-top-style: none; display: inline; margin-bottom: 2px\" width=\"24\"></span></a><img id=\"_x0000_i1029\" border=\"0\" class=\"\" src=\"https://ci6.googleusercontent.com/proxy/wu_nasMQvgujkWqFw0VMsW-Du2jSo6681tevUA0WRlUscqtAYCqt46KjsfRnLT8nNNuyZ9gHcUSlvXRbY0U2Ki4BGp5uzmgE=s0-d-e1-ft#https://s3.amazonaws.com/htmlsig-assets/spacer.gif\" width=\"2\">&nbsp;<a href=\"https://plus.google.com/+userName\" target=\"_blank\"><span style=\"text-decoration: none; text-underline: none\"><img id=\"_x0000_i1030\" alt=\"Google +\" border=\"0\" class=\"\" height=\"24\" src=\"https://ci4.googleusercontent.com/proxy/RBkT2quvjNzOzyRwCsdf-Z2Bae6ZVTNka8ZLKeSSPtndSqfIz3M9u6CEI6t5N2kCZj2sqNkFJLxs8Q3zyX6SiDfGrCHKtM1i_TH2lQlXsSs_=s0-d-e1-ft#https://s3.amazonaws.com/htmlsig-assets/grey/googleplus.png\" style=\"border-bottom-style: none; border-left-style: none; border-right-style: none; border-top-style: none; display: inline; margin-bottom: 2px\" width=\"24\"></span></a><img id=\"_x0000_i1031\" border=\"0\" class=\"\" src=\"https://ci6.googleusercontent.com/proxy/wu_nasMQvgujkWqFw0VMsW-Du2jSo6681tevUA0WRlUscqtAYCqt46KjsfRnLT8nNNuyZ9gHcUSlvXRbY0U2Ki4BGp5uzmgE=s0-d-e1-ft#https://s3.amazonaws.com/htmlsig-assets/spacer.gif\" width=\"2\">&nbsp;<a href=\"https://instagram.com/userName/\" target=\"_blank\"><span style=\"text-decoration: none; text-underline: none\"><img id=\"_x0000_i1032\" alt=\"Instagram\" border=\"0\" class=\"\" height=\"24\" src=\"https://ci6.googleusercontent.com/proxy/0jmM5rIMKXTDNMzPTTRnd_1vz_GCS0paa-RYucFHtEtEUnEPNVBEeX8yIDidI9vvGkCsQbXWbgJb247Gizrj3Vfd-RF_AVqWmSeAjLPchnY=s0-d-e1-ft#https://s3.amazonaws.com/htmlsig-assets/grey/instagram.png\" style=\"border-bottom-style: none; border-left-style: none; border-right-style: none; border-top-style: none; display: inline; margin-bottom: 2px\" width=\"24\"></span></a><img id=\"_x0000_i1033\" border=\"0\" class=\"\" src=\"https://ci6.googleusercontent.com/proxy/wu_nasMQvgujkWqFw0VMsW-Du2jSo6681tevUA0WRlUscqtAYCqt46KjsfRnLT8nNNuyZ9gHcUSlvXRbY0U2Ki4BGp5uzmgE=s0-d-e1-ft#https://s3.amazonaws.com/htmlsig-assets/spacer.gif\" width=\"2\">&nbsp;<a href=\"https://www.youtube.com/c/channelName\" target=\"_blank\"><span style=\"text-decoration: none; text-underline: none\"><img id=\"_x0000_i1034\" alt=\"Youtube\" border=\"0\" class=\"\" height=\"24\" src=\"https://ci6.googleusercontent.com/proxy/Y0ATJVQzdFb44ccT30VI_nsoky3z6hNNIuR7gNbMu7INrq0MKhs1o3CNHINI6GW5hpRbxgY7n8m0nNYw96W5jhhMbIWYe_Mcf8N-H0VF=s0-d-e1-ft#https://s3.amazonaws.com/htmlsig-assets/grey/youtube.png\" style=\"border-bottom-style: none; border-left-style: none; border-right-style: none; border-top-style: none; display: inline; margin-bottom: 2px\" width=\"24\"></span></a><o:p></o:p></span></p>";
        }
        else{
            socialM = "";
            socialMVT = "";
        }
        readHtml(); //Grab HTML
        } catch(Exception e) {
            Alert alert = new Alert(AlertType.ERROR, e.getMessage());
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image logo = new Image(getClass().getResourceAsStream("CompanyLogo.png"));
        imgCompanyLogo.setImage(logo);
    } // End initialize
    
    private boolean nameVerify(String name){
     if(name.length() >= 2)
         return(true);
     else
        return(false);
    } // End method nameVerify()
    
    private boolean phoneVerify(String phone){
        if(phone.matches("\\b\\d{3}[-.]\\d{3}[-.]\\d{4}\\b"))
         return(true);
     else
        return(false);
    } // End method phoneVerify()
    
    private void readHtml(){ //This is the Generated HTML in the WebView
        engine = webView.getEngine();
        engine.loadContent("<html>" +
        "   <head>" +
        "      <style>" +
        "       p {" +
        "          margin: 0;" +                
        "          padding: 1 0;" +
        "          font-size: 9pt;" +
        "          color: #888888;" +
        "          font-family: Tahoma;" +
        "          }" +
        "       .vt {" +
        "          font-size: 13pt;" +
        "          font-family: Helvetica;" +
        "          margin: 0;" +                
        "          padding: .5px 1px;" +               
        "          }" +
        "       .vtcolor {" +
        "          color: #1ba7b9;" +
        "          }" +
        "       .green {" +
        "          color: green" +
        "          }" +
        "       .black {" +
        "          color: black;" +
        "          }" +
        "       .nounder {" +
        "          text-decoration: none;" +
        "          }" +
        "      </style>" +
        "   </head>" +
        "   <body>\n" +
        "       \n" +
        "       <a href=\"http://www.company1.com\"><img src=\"http://www.nstuttle.com/images/sigGen/CompanyLogo1.png\" border=\"0\" alt=\"Company 1 Logo\"></a>\n" +
        "       <p>"+firstName+" "+lastName+" | "+title+"</p>\n" +
        "       <p>Phone: "+directLine+" | Fax: 555-555-5555" + cellPhone + "</p>\n" +
        "       <p>"+firstName+"."+lastName+"@company1.com | <a href=\"http://www.comapny1.com\">www.Company1.com</a></p>" +
                socialM +
        "       <p>&nbsp;</p>" +
        "       <p class=\"green\"><img src=\"http://lib.store.yahoo.net/lib/kvm-switch/webdingsGreen2.jpg\"> Please don't print this email unless it's necessary.</p>\n" +
        "       <br>\n" +
        "       <br>\n" +
        "       <br>\n" +
        "       <a href=\"http://www.company2.com\"><img src=\"http://www.nstuttle.com/images/sigGen/CompanyLogo2.png\" border=\"0\" alt=\"Company 2 Logo\"></a>\n" +
        "       <p>"+firstName+" "+lastName+" | "+title+"</p>\n" +
        "       <p>Phone: "+directLine+" | Fax: 555-555-5555" + cellPhone + "</p>\n" +
        "       <p>"+firstName+"."+lastName+"@company2.com | <a href=\"http://www.company2.com\">www.Company2.com</a></p>\n" +
                socialM + 
        "       <p>&nbsp;</p>" +
        "       <p class=\"green\"><img src=\"http://lib.store.yahoo.net/lib/kvm-switch/webdingsGreen2.jpg\"> Please don't print this email unless it's necessary.</p>\n" +
        "       <br>\n" +
        "       <br>\n" +
        "       <br>\n" +
        "       <a href=\"http://www.company3.com\"><img src=\"http://www.nstuttle.com/images/sigGen/CompanyLogo3.png\" border=\"0\" alt=\"Company 3 Logo\"></a>\n" +
        "       <p>"+firstName+" "+lastName+" | "+title+"</p>\n" +
        "       <p>Phone: "+directLine+" | Fax: 555-555-5555" + cellPhone + "</p>\n" +
        "       <p>"+firstName+"."+lastName+"@company3.com | <a href=\"http://www.company3.com\">www.Server-Rack-Online.com</a></p>\n" +
                socialM + 
        "       <p>&nbsp;</p>" +
        "       <p class=\"green\"><img src=\"http://lib.store.yahoo.net/lib/kvm-switch/webdingsGreen2.jpg\"> Please don't print this email unless it's necessary.</p>\n" +
        "       <br>\n" +
        "       <br>\n" +
        "       <br>\n" +
        "       <a href=\"http://www.company4.com\"><img src=\"http://www.nstuttle.com/images/sigGen/CompanyLogo4.png\" border=\"0\" alt=\"Company 4 Logo\"></a>\n" +
        "       <p>"+firstName+" "+lastName+" | "+title+"</p>\n" +
        "       <p>Phone: "+directLine+" | Fax: 555-555-5555" + cellPhone + "</p>\n" +
        "       <p>"+firstName+"."+lastName+"@company4.com | <a href=\"http://www.company4.com\">www.Company4.com</a></p>\n" +
                socialM + 
        "       <p>&nbsp;</p>" +
        "       <p class=\"green\"><img src=\"http://lib.store.yahoo.net/lib/kvm-switch/webdingsGreen2.jpg\"> Please don't print this email unless it's necessary.</p>\n" +
        "       <br>\n" +
        "       <br>\n" +
        "       <br>\n" +
        "       <a href=\"http://www.company5.com\"><img src=\"http://www.nstuttle.com/images/sigGen/CompanyLogo5.png\" border=\"0\" alt=\"Company 5 Logo\"></a>\n" +
        "       <p>"+firstName+" "+lastName+" | "+title+"</p>\n" +
        "       <p>Phone: "+directLine+" | Fax: 555-555-5555" + cellPhone + "</p>\n" +
        "       <p>"+firstName+"."+lastName+"@company5.com | <a href=\"http://www.company5.com\">www.Company5.com</a></p>\n" +
        "       <p>&nbsp;</p>" +
        "       <p class=\"green\"><img src=\"http://lib.store.yahoo.net/lib/kvm-switch/webdingsGreen2.jpg\"> Please don't print this email unless it's necessary.</p>\n" +
        "       <br>\n" +
        "       <br>\n" +
        "       <br>\n" +
        "       <a href=\"http://www.company6.com\"><img src=\"http://www.nstuttle.com/images/sigGen/CompanyLogo6.png\" border=\"0\" alt=\"Company 6 Logo\"></a>\n" +
        "       <p class=\"vt black\"><b>"+firstName+" "+lastName + "</b>" +
        "       <p class=\"vt vtcolor\">"+firstName+"@company6.com</p>\n" +
        "       <p>&nbsp;</p>" +
        "       <p class=\"vt black\"><b>Company 6</b></p>\n" +
        "       <p class=\"vt black\">" + directLine + "</p>\n" +
        "       <p class=\"vt vtcolor\"><a class=\"nounder vt vtcolor\"href=\"http://www.company6.com\">Company6.com</a></p>" +
                socialMVT +
        "       <p>&nbsp;</p>" +
        "       <p class=\"green\"><img src=\"http://lib.store.yahoo.net/lib/kvm-switch/webdingsGreen2.jpg\"> Please don't print this email unless it's necessary.</p>\n" +
        "   </body>\n" +
        "</html>"); //End loadContent() method
    }// End method readHtml()
}// End class SignatureGenFXMLDocController
