#### Split, rebased

Imagine you implemented two features and made one commit. Your tech lead said to split it in two.

You need:
- split one commit (`git-course_5_feature` branch) in two:
  - `306: Add prod to allowed origins` - changes in `/src/main/resources/application.properties`
  - `304: Add more hotels with migration` - other changes
- rebase onto dev branch (`git-course_5_dev`)
