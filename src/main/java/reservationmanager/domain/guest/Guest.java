package reservationmanager.domain.guest;

import com.google.common.base.Strings;

public class Guest {
    private String name;
    private String address;
    private String tel;

    public Guest(String name, String address, String tel) {
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public boolean isContactable() {
        return !(Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(tel));
    }
}
