#### Split, rebased

Imagine you fixed a defect (`git-course_8_defects` branch).
Your tech lead said that in fact, these are two different defects.

You need:
- split one commit (`git-course_8_defects` branch) in two:
* `7013: Update controller` - changes in `src/main/java/hotels/controllers/SignUp.java` and `src/main/java/hotels/RunApplication.java`
* `7012: Fix cors` - other changes
- rebase onto `dev` branch (`git-course_8_dev`)
