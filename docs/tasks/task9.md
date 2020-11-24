#### Change commits

Imagine you implemented features (`git-course_9_feature` branch).
Your tech lead said you to correct some changes.

You need:
- remove all changes in `pom.xml` file in `139532: Add Category page backend` commit
- rename `ownedHotels` variable to `ownedHotelsNew` in `src/main/java/hotels/services/Impls/HotelServiceImpl.java` file
in the first `139532: Add more hotels with migration` commit
- rename all `zip` variables and methods to `myZip` in `src/main/java/hotels/models/Location.java` file
in `139596: Add single hotel page backend` commit
- change password pattern to `^(?=.*\\d{1,})(?=.*[a-z]{1,})(?=.*[A-Z]{1,})(?=.*[#?!@$%^&*-]{1,}).{6,40}$`
in `src/main/java/hotels/services/Impls/UserServiceImpl.java` file
in `140271: Change field name/update password regex/update default value` commit
