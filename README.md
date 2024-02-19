# Android challenge
We would like to learn more about how you approach a challenge as an Android Engineer.
There is a simple app that isn’t made with best practices in mind and works perfectly in the happy path.

The challenge will be composed of a pair programming session, where we are going to improve and fix some issues with an application that has a simple requirement:

```
As a potential traveler, I want to be able to fetch reviews for one of our most popular Berlin tours.
```

We’ll evaluate platform, architecture, testing, and problem solving-knowledge.

What you’ll need:
- Java 17 and Android Studio configured

# Solution
is a sample for clean architecture based on MVI architecture pattern that the UI depend on single immutable state that demonstrate the screen.

# Decision
The dealing with architecture patten depend on states idea is so important and adding more concrete structure more than MVVM as:
- The ui depend on single source of truth (only one state) instead of many channel that emitting the data.
- The ui starting with initial state which decrease the possibilities of handling null references and missing data.
- Every state depend on the previous state so no state come out of out of nowhere. This is so good as the screen can demonstrated through finite state machine and have fixed immutable state any time.