import com.store.AppUser;
import com.store.AppUserAuthRole;
import com.store.AuthRole

class BootStrap {

    def init = { servletContext ->
		def adminRole  = AuthRole.findOrSaveWhere(authority: "ROLE_ADMIN")
		def userRole = AuthRole.findOrSaveWhere(authority: "ROLE_USER")
		def admin = AppUser.findOrSaveWhere(username:"admin", password:"pwd")
		def user  = AppUser.findOrSaveWhere(username:"user", password:"pwd")
		AppUserAuthRole.create(admin, adminRole, true)
		AppUserAuthRole.create(user, userRole, true)
    }
    def destroy = {
    }
}
