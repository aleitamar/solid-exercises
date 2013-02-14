package com.theladders.solid.dip;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RepositorySubscriberArticleRetriever implements SubscriberArticleRetriever
{
  private static final String                  IMAGE_PREFIX       = "http://somecdnprodiver.com/static/images/careerAdvice/";
  private static final HashMap<Object, Object> CATEGORY_IMAGE_MAP = new HashMap<>();
  
  private SuggestedArticleDao suggestedArticleDao;
  private RepositoryManager repositoryManager;

  static
  {
    CATEGORY_IMAGE_MAP.put("Interviewing", "interviewing_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Job Search", "job_search_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Networking", "networking_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Personal Branding", "personalBranding_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Resume", "resume_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Salary", "salary_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Assessment", "salary_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("On the Job", "salary_thumb.jpg");
  }


  public RepositorySubscriberArticleRetriever(SuggestedArticleDao suggestedArticleDao, RepositoryManager repositoryManager)
  {
    this.suggestedArticleDao = suggestedArticleDao;
    this.repositoryManager = repositoryManager;
  }

  public List<SuggestedArticle> getArticlesbySubscriber(Integer subscriberId)
  {
    SuggestedArticleExample criteria = new SuggestedArticleExample();
    criteria.createCriteria()
    .andSubscriberIdEqualTo(subscriberId)
    .andSuggestedArticleStatusIdIn(Arrays.asList(1, 2))
    .andSuggestedArticleSourceIdEqualTo(1);

    criteria.setOrderByClause("create_time desc");
    List<SuggestedArticle> dbSuggestions = this.suggestedArticleDao.selectByExampleWithBlobs(criteria);

    resolveArticles(dbSuggestions);

    return dbSuggestions;
  }

  private void resolveArticles(List<SuggestedArticle> dbArticles)
  {
    for (SuggestedArticle article : dbArticles)
    {
      ContentNode content = this.repositoryManager.getNodeByUuid(article.getArticleExternalIdentifier());
      if (content != null && ContentUtils.isPublishedAndEnabled(content))
      {
        overrideMiniImagePath(content);
        article.setContent(content);
      }
    }
  }

  private static void overrideMiniImagePath(ContentNode node)
  {
    String path = (String) node.getProperty("miniImagePath");

    if (path == null || path.length() == 0)
    {
      String category = (String) node.getProperty("primaryTopic");
      node.addProperty("miniImagePath", IMAGE_PREFIX + CATEGORY_IMAGE_MAP.get(category));
    }
  }
}
