#### Split, rebased

Imagine you fixed a defect (`git-course_2_defects` branch).
Your tech lead said that in fact, these are two different defects.

You need:
- split one commit (`git-course_2_defects` branch) in two:
* `1023: Fix cors` - added file `/src/main/java/hotels/security/SecurityConfig.java`
* `1038: Fix deployment` - other changes
- rebase onto `dev` branch (`git-course_2_dev`)
- resolve conflicts (all your changes are more important)
