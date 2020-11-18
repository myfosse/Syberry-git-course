#### Squash, cherry pick, resolve conflicts

Imagine you had to implement two features - `sign up` and `sign in` (`sign in` depends on `sign up`).
You made the first one and created a merge request for it.
Then you started to implement the second one (`git-course_1_feature` branch).

After a while notes for the first MR (`sign up`) arrived. You fixed it.
The feature was merged into `dev` branch (`git-course_1_dev`).
You finished the second feature (`sign in`).

You need:
- squash your changes by `sign in` feature (`git-course_1_feature` branch) into one commit 
according to the guidelines (one feature - one commit)
- rebase onto `dev` branch (`git-course_1_dev`).
`Add sign up back end` commit with outdated code from your branch (`git-course_1_feature`) should not go to the `dev` branch
- resolve conflicts (all your changes in `sign in` feature are more important)
