package com.theladders.solid.dip;

import java.util.Date;

public class RepositorySubscriberArticleWriter implements SubscriberArticleWriter
{
  private SuggestedArticleDao suggestedArticleDao;

  public RepositorySubscriberArticleWriter(SuggestedArticleDao suggestedArticleDao)
  {
    this.suggestedArticleDao = suggestedArticleDao;
  }
  
  public int addSuggestedArticle(SuggestedArticle suggestedArticle)
  {
    Integer STATUS_UNREAD = 1;
    Integer HTP_CONSULTANT = 1;
    suggestedArticle.setSuggestedArticleStatusId(STATUS_UNREAD);
    suggestedArticle.setSuggestedArticleSourceId(HTP_CONSULTANT);
    suggestedArticle.setCreateTime(new Date()); // current date
    suggestedArticle.setUpdateTime(new Date()); // current date
    int newId = suggestedArticleDao.insertReturnId(suggestedArticle);
    return newId;
  }

  public void updateNote(Integer id, String note)
  {
    SuggestedArticle article = new SuggestedArticle();
    article.setSuggestedArticleId(id);
    article.setNote(note);
    suggestedArticleDao.updateByPrimaryKeySelective(article);
  }

  public void markRecomDeleted(Integer id)
  {
    Integer STATUS_DELETED = 4;
    SuggestedArticle article = new SuggestedArticle();
    article.setSuggestedArticleId(id);
    article.setSuggestedArticleStatusId(STATUS_DELETED);
    suggestedArticleDao.updateByPrimaryKeySelective(article);
  }
}
