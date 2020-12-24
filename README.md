# Hotels Project Backend API

You should have Java 11, Maven locally installed (will be moved to Docker later).

How to install:

1. Clone the repo:

```bash
$ git clone git@git.syberry.com:syberry-internship/hotels-backend.git
```

2. Add `sa-hotels.loc` to `hosts`:

```
127.0.0.1 sa-hotels.loc
```

3. Build a package (this step will be deleted after full Docker integration)

```
$ mvnw package
```

4. Run the `docker-compose up` from the root:

```
$ docker-compose up
```

5. Open http://sa-hotels.loc in browser. Enjoy
