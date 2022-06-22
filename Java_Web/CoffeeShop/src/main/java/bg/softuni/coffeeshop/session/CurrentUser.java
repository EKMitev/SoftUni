package bg.softuni.coffeeshop.session;

import bg.softuni.coffeeshop.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;
    private String username;
    private String lastName;
    private String email;

    public Long getId() {
        return id;
    }

    public void setLogged(User user) {
        this
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail());
    }

    public boolean isLogged(){
        return this.getId() != null;
    }

    public void clear() {
        this
                .setId(null)
                .setUsername(null)
                .setLastName(null)
                .setEmail(null);
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CurrentUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CurrentUser setEmail(String email) {
        this.email = email;
        return this;
    }
}
