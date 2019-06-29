package reservationmanager.domain.guest;

import com.google.common.base.Strings;
import org.jetbrains.annotations.NotNull;

public class Guest {
    private String name;
    private String address;
    private String tel;

    public Guest(@NotNull String name,@NotNull String address,@NotNull String tel) {
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public boolean isContactable() {
        return !(Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(tel));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guest guest = (Guest) o;

        if (!name.equals(guest.name)) return false;
        if (!address.equals(guest.address)) return false;
        return tel.equals(guest.tel);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + tel.hashCode();
        return result;
    }
}
