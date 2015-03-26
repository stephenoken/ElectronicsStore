package com.store

import org.apache.commons.lang.builder.HashCodeBuilder

class AppUserAuthRole implements Serializable {

	private static final long serialVersionUID = 1

	AppUser appUser
	AuthRole authRole

	boolean equals(other) {
		if (!(other instanceof AppUserAuthRole)) {
			return false
		}

		other.appUser?.id == appUser?.id &&
		other.authRole?.id == authRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (appUser) builder.append(appUser.id)
		if (authRole) builder.append(authRole.id)
		builder.toHashCode()
	}

	static AppUserAuthRole get(long appUserId, long authRoleId) {
		AppUserAuthRole.where {
			appUser == AppUser.load(appUserId) &&
			authRole == AuthRole.load(authRoleId)
		}.get()
	}

	static boolean exists(long appUserId, long authRoleId) {
		AppUserAuthRole.where {
			appUser == AppUser.load(appUserId) &&
			authRole == AuthRole.load(authRoleId)
		}.count() > 0
	}

	static AppUserAuthRole create(AppUser appUser, AuthRole authRole, boolean flush = false) {
		def instance = new AppUserAuthRole(appUser: appUser, authRole: authRole)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(AppUser u, AuthRole r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = AppUserAuthRole.where {
			appUser == AppUser.load(u.id) &&
			authRole == AuthRole.load(r.id)
		}.deleteAll()

		if (flush) { AppUserAuthRole.withSession { it.flush() } }

		rowCount > 0
	}

	static void removeAll(AppUser u, boolean flush = false) {
		if (u == null) return

		AppUserAuthRole.where {
			appUser == AppUser.load(u.id)
		}.deleteAll()

		if (flush) { AppUserAuthRole.withSession { it.flush() } }
	}

	static void removeAll(AuthRole r, boolean flush = false) {
		if (r == null) return

		AppUserAuthRole.where {
			authRole == AuthRole.load(r.id)
		}.deleteAll()

		if (flush) { AppUserAuthRole.withSession { it.flush() } }
	}

	static constraints = {
		authRole validator: { AuthRole r, AppUserAuthRole ur ->
			if (ur.appUser == null) return
			boolean existing = false
			AppUserAuthRole.withNewSession {
				existing = AppUserAuthRole.exists(ur.appUser.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['authRole', 'appUser']
		version false
	}
}
