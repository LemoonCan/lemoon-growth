package designpattern.behavior.mediator;

import javafx.scene.text.Text;

import java.awt.*;

/**
 * @author lee
 * @date 2022/10/26
 */
public class LandingDialog implements Mediator {
    private TextField usernameInput;
    private TextField passwordInput;
    private TextField repeatedPasswordInput;

    private Button logButton;
    private Button regButton;

    private Label hintText;

    public LandingDialog(TextField usernameInput, TextField passwordInput, TextField repeatedPasswordInput, Button logButton, Button regButton) {
        this.usernameInput = usernameInput;
        this.passwordInput = passwordInput;
        this.repeatedPasswordInput = repeatedPasswordInput;
        this.logButton = logButton;
        this.regButton = regButton;
        this.hintText = new Label();
    }


    @Override
    public void handleEvent(Component component, String event) {
        if (component.equals(logButton)) {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            //登录
        } else if (component.equals(regButton)) {
            //注册
        } else if (component.equals(repeatedPasswordInput)) {
            //校验首次输入密码与重复输入密码是否一致
        }
        //...
    }
}
