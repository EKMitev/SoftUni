package bg.softuni.battleships.session;

import bg.softuni.battleships.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;
    private String username;
    private String fullName;
    private String email;

    public Long getId() {
        return id;
    }

    public void setLogged(User user) {
        this
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setFullName(user.getFullName())
                .setEmail(user.getEmail());
    }

    public boolean isLogged(){
        return this.getId() != null;
    }

    public void clear() {
        this
                .setId(null)
                .setUsername(null)
                .setFullName(null)
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

    public String getFullName() {
        return fullName;
    }

    public CurrentUser setFullName(String fullName) {
        this.fullName = fullName;
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
