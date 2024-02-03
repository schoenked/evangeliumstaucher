# SearchApi

All URIs are relative to *https://api.scripture.api.bible*

Method | HTTP request | Description
------------- | ------------- | -------------
[**searchBible**](SearchApi.md#searchBible) | **GET** /v1/bibles/{bibleId}/search | 


<a name="searchBible"></a>
# **searchBible**
> InlineResponse20011 searchBible(bibleId, query, limit, offset, sort, range, fuzziness)



Gets search results for a given &#x60;bibleId&#x60; and query string.  Searches will match all verses with the list of keywords provided in the query string. Order of the keywords does not matter. However all keywords must be present in a verse for it to be considered a match. The total number of results returned from a search can be limited by populating the &#x60;limit&#x60; attribute in the query string with a non-negative integer value.  If no limit value is provide a default of 10 is used. &#x60;offset&#x60; can be used to traverse paginated results.  So for example if you are using the default &#x60;limit&#x60; of 10, using an &#x60;offset&#x60; of 10 will return the second page of results, namely results 11-20. The &#x60;text&#x60; property of each verse object contains only the verse text.  It does not contain footnote references. However, those can be queried directly using the &#x60;/bibles/{bibleId}/verses/{verseId}&#x60; endpoint. 

### Example
```java
// Import classes:
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.auth.*;
import de.evangeliumstaucher.invoker.models.*;
import de.evangeliumstaucher.SearchApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.scripture.api.bible");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    SearchApi apiInstance = new SearchApi(defaultClient);
    String bibleId = "bibleId_example"; // String | Id of Bible to search
    String query = "query_example"; // String | Search keywords or passage reference.  Supported wildcards are * and ?.   The * wildcard matches any character sequence (e.g. searching for \"wo*d\" finds text such as \"word\", \"world\", and \"worshipped\").   The ? wildcard matches any matches any single character (e.g. searching for \"l?ve\" finds text such as \"live\" and \"love\"). 
    Integer limit = 56; // Integer | Integer limit for how many matching results to return. Default is 10.
    Integer offset = 56; // Integer | Offset for search results. Used to paginate results
    String sort = "relevance"; // String | Sort order of results.  Supported values are `relevance` (default), `canonical` and `reverse-canonical`
    String range = "range_example"; // String | One or more, comma seperated, passage ids (book, chapter, verse) which the search will be limited to.  (i.e. gen.1,gen.5 or gen-num or gen.1.1-gen.3.5) 
    String fuzziness = "AUTO"; // String | Sets the fuzziness of a search to account for misspellings. Values can be 0, 1, 2, or AUTO. Defaults to AUTO which varies depending on the 
    try {
      InlineResponse20011 result = apiInstance.searchBible(bibleId, query, limit, offset, sort, range, fuzziness);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SearchApi#searchBible");
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
 **bibleId** | **String**| Id of Bible to search |
 **query** | **String**| Search keywords or passage reference.  Supported wildcards are * and ?.   The * wildcard matches any character sequence (e.g. searching for \&quot;wo*d\&quot; finds text such as \&quot;word\&quot;, \&quot;world\&quot;, and \&quot;worshipped\&quot;).   The ? wildcard matches any matches any single character (e.g. searching for \&quot;l?ve\&quot; finds text such as \&quot;live\&quot; and \&quot;love\&quot;).  | [optional]
 **limit** | **Integer**| Integer limit for how many matching results to return. Default is 10. | [optional]
 **offset** | **Integer**| Offset for search results. Used to paginate results | [optional]
 **sort** | **String**| Sort order of results.  Supported values are &#x60;relevance&#x60; (default), &#x60;canonical&#x60; and &#x60;reverse-canonical&#x60; | [optional] [default to relevance] [enum: relevance, canonical, reverse-canonical]
 **range** | **String**| One or more, comma seperated, passage ids (book, chapter, verse) which the search will be limited to.  (i.e. gen.1,gen.5 or gen-num or gen.1.1-gen.3.5)  | [optional]
 **fuzziness** | **String**| Sets the fuzziness of a search to account for misspellings. Values can be 0, 1, 2, or AUTO. Defaults to AUTO which varies depending on the  | [optional] [enum: AUTO, 0, 1, 2]

### Return type

[**InlineResponse20011**](InlineResponse20011.md)

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
**404** | Section not found |  -  |

