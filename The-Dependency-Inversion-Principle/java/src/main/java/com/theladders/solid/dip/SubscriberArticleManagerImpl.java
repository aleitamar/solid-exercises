package com.theladders.solid.dip;

import java.util.List;

public class SubscriberArticleManagerImpl implements SubscriberArticleManager
{
  private final SubscriberArticleRetriever subscriberArticleRetriever;
  private SubscriberArticleWriter subscriberArticleWriter;

  public SubscriberArticleManagerImpl(SubscriberArticleRetriever subscriberArticleRetriever,
                                      SubscriberArticleWriter subscriberArticleWriter)
  {
    this.subscriberArticleRetriever = subscriberArticleRetriever;
    this.subscriberArticleWriter = subscriberArticleWriter;
  }

  public List<SuggestedArticle> getArticlesbySubscriber(Integer subscriberId)
  {
    return subscriberArticleRetriever.getArticlesbySubscriber(subscriberId);
  }

  public int addSuggestedArticle(SuggestedArticle suggestedArticle)
  {
    return subscriberArticleWriter.addSuggestedArticle(suggestedArticle);
  }

  public void updateNote(Integer id, String note)
  {
    subscriberArticleWriter.updateNote(id, note);
  }

  public void markRecomDeleted(Integer id)
  {
    subscriberArticleWriter.markRecomDeleted(id);
  }
}