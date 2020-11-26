#### Squash, cherry-pick, resolve conflicts

You're working on the project. <br>
Your task was to implement two features - `sign up` and `sign in` (`sign in` depends on `sign up`). <br>
You developed the `sign up` feature and created a merge request for it. <br>
After that you started implementing the `sign in` feature (`git-course_1_feature` branch). <br>

After a while, your TL commented on your first MR (`sign up`). You refined the code following the comments. <br>
The feature was merged into `dev` branch (`git-course_1_dev`). <br>
You finished the second feature (`sign in`).

Your task is to
- squash your changes made in `sign in` feature (`git-course_1_feature` branch) into one commit according to the guidelines (one feature - one commit)
- rebase onto `dev` branch (`git-course_1_dev`) <br>
`Add sign up back end` commit with outdated code from your branch (`git-course_1_feature`) should not go to the `dev` branch
- resolve conflicts (all your changes in `sign in` feature are more important)
- make screenshots confirming each step and screenshots of the result
- add screenshots to `screenshots` folder
