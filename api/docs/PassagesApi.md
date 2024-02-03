# PassagesApi

All URIs are relative to *https://api.scripture.api.bible*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getPassage**](PassagesApi.md#getPassage) | **GET** /v1/bibles/{bibleId}/passages/{passageId} | 


<a name="getPassage"></a>
# **getPassage**
> InlineResponse2006 getPassage(bibleId, passageId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels, useOrgId)



Gets a &#x60;Passage&#x60; object for a given &#x60;bibleId&#x60; and &#x60;passageId&#x60;. This Passage object also includes an &#x60;content&#x60; property with all verses corresponding to the passageId. The &#x60;passageId&#x60; parameter can represent a chapter, verse, or range of verses. 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.PassagesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    PassagesApi apiInstance = new PassagesApi(defaultClient);
    String bibleId = "bibleId_example"; // String | Id of Bible for passage
    String passageId = "passageId_example"; // String | String reference id for the requested passage.
    String contentType = "html"; // String | Content type to be returned in the content property.  Supported values are `html` (default), `json` (beta), and `text` (beta)
    Boolean includeNotes = false; // Boolean | Include footnotes in content
    Boolean includeTitles = true; // Boolean | Include section titles in content
    Boolean includeChapterNumbers = false; // Boolean | Include chapter numbers in content
    Boolean includeVerseNumbers = true; // Boolean | Include verse numbers in content.
    Boolean includeVerseSpans = false; // Boolean | Include spans that wrap verse numbers and verse text for bible content.
    String parallels = "parallels_example"; // String | Comma delimited list of bibleIds to include
    Boolean useOrgId = false; // Boolean | Use the supplied id(s) to match the verseOrgId instead of the verseId
    try {
      InlineResponse2006 result = apiInstance.getPassage(bibleId, passageId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels, useOrgId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PassagesApi#getPassage");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **bibleId** | **String**| Id of Bible for passage |
 **passageId** | **String**| String reference id for the requested passage. |
 **contentType** | **String**| Content type to be returned in the content property.  Supported values are &#x60;html&#x60; (default), &#x60;json&#x60; (beta), and &#x60;text&#x60; (beta) | [optional] [default to html] [enum: html, json, text]
 **includeNotes** | **Boolean**| Include footnotes in content | [optional] [default to false]
 **includeTitles** | **Boolean**| Include section titles in content | [optional] [default to true]
 **includeChapterNumbers** | **Boolean**| Include chapter numbers in content | [optional] [default to false]
 **includeVerseNumbers** | **Boolean**| Include verse numbers in content. | [optional] [default to true]
 **includeVerseSpans** | **Boolean**| Include spans that wrap verse numbers and verse text for bible content. | [optional] [default to false]
 **parallels** | **String**| Comma delimited list of bibleIds to include | [optional]
 **useOrgId** | **Boolean**| Use the supplied id(s) to match the verseOrgId instead of the verseId | [optional] [default to false]

### Return type

[**InlineResponse2006**](InlineResponse2006.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful response |  -  |
**400** | Invalid ID supplied |  -  |
**401** | Unauthorized for API access.  Missing or Invalid API Token provided. |  -  |
**403** | Not authorized to retrieve Sections for this Bible |  -  |
**404** | Passage not found |  -  |

