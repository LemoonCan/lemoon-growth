package designpattern.behavior.mediator;

import java.awt.*;

/**
 * @author lee
 * @date 2022/10/26
 */
public class UIControl {
    public static void main(String[] args) {
        TextField usernameInput = new TextField();
        TextField passwordInput = new TextField();
        TextField repeatedPasswordInput = new TextField();

        Button logButton = new Button("登录");
        Button regButton = new Button("注册");
        Mediator landingDialog = new LandingDialog(usernameInput,passwordInput,repeatedPasswordInput,logButton,regButton);

        logButton.addActionListener(e -> landingDialog.handleEvent(logButton,"click"));
        regButton.addActionListener(e -> landingDialog.handleEvent(regButton,"click"));

        //...
    }
}
