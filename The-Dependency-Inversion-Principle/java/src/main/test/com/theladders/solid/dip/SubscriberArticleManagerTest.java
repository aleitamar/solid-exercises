package com.theladders.solid.dip;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class SubscriberArticleManagerTest
{
  @Test
  public void subscriberArticleManagerWithRepositoryBackupCanRetrieveArticles()
  {
    SubscriberArticleManagerImpl subscriberArticleManager = getSubscriberArticleManager(); 
    
    List<SuggestedArticle> articles = subscriberArticleManager.getArticlesbySubscriber(1);
    
    assertEquals(1, articles.size());
  }
  
  @Test
  public void subscriberArticleManagerWithRepositoryBackupCanSetNote()
  {
    SubscriberArticleManagerImpl subscriberArticleManager = getSubscriberArticleManager(); 
    
    List<SuggestedArticle> articles = subscriberArticleManager.getArticlesbySubscriber(1);
    subscriberArticleManager.updateNote(articles.get(0).getid(), "this is a note");
    //some assertion
  }
  
  private static SubscriberArticleManagerImpl getSubscriberArticleManager()
  {
    SuggestedArticleDao suggestedArticleDao = new SuggestedArticleDao();
    RepositoryManager repositoryManager = new RepositoryManager();
    
    RepositorySubscriberArticleWriter subscriberArticleWriter = new RepositorySubscriberArticleWriter(suggestedArticleDao);
    RepositorySubscriberArticleRetriever subscriberArticleRetriever = new RepositorySubscriberArticleRetriever(suggestedArticleDao, repositoryManager);
    
    return new SubscriberArticleManagerImpl(subscriberArticleRetriever, subscriberArticleWriter);
    
  }

}