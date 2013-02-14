TheLadders' SOLID Exercises - The Dependency Inversion Principle
===============

Watch [the code-cast](http://www.cleancoders.com/codecast/clean-code-episode-13/show) then dig into the exercise.

---

At its core, the Dependency Inversion Principle states that "High level policy should not depend on low level detail. Low level detail should depend on high level policy." With that in mind, first take a look at SuggestedArticleExample.

* What direction will the dependencies flow in code that uses this class?
  ** -> Any of the code that uses this class with depend entirely on this class for building the query to access objects from the database.
* Does this class represent a violation of the Principle?
  ** -> I'd say it does. You see some exposure of an sql based implementation here in a file that could contain generic ordering and conditional record querying dsl. It could be implementing an
        interface called RecordRetriever or the like that implements an abstract way to specify the retrieval of information.
* Do the use of ORM tools necessarily cause violations?
  ** -> I don't think so. In fact, they often allow the ability for the objects they create for use in the application to act as interfaces to the storage layer.
        A good example of this is ActiveRecord. The models you create have methods that deal with saving and retrieving data, but are not tied to how these methods are actually stored.
        It is possible to swap out Postgres, filestore, or redis without changing the model in the least. But, as soon as you start writing code that refers specifically to a storage mechanism
        you are breaking this. An example can be found in the SuggestedArticleExample class when there is sql getting built up in the inner class.

Now look at SubscriberArticleManagerImpl. Notice that there are many direct dependencies on systems that should be handled as plug-ins instead.

* What systems / modules does this class depend on?
  ** -> SuggestedArticleDao,
        RepositoryManager,
        SuggestedArticleExample,
        ContentNode,
        SuggestedArticle
* Which of these are compile-time dependencies? Which of these are run-time dependencies?
  ** -> SuggestedArticleDao      Run-time 
        RepositoryManager        Run-time
        SuggestedArticleExample  Compile-time 
        ContentNode              Run-time 
        SuggestedArticle         Both      

Try refactoring the SubscriberArticleManager so that other modules act as plugins into it. You'll likely have to tangle with the Single Responsibility Principle as well.
