/*
 * API.Bible
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.6.3
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package de.evangeliumstaucher;

import de.evangeliumstaucher.invoker.ApiCallback;
import de.evangeliumstaucher.invoker.ApiClient;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.invoker.ApiResponse;
import de.evangeliumstaucher.invoker.Configuration;
import de.evangeliumstaucher.invoker.Pair;
import de.evangeliumstaucher.invoker.ProgressRequestBody;
import de.evangeliumstaucher.invoker.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import de.evangeliumstaucher.model.InlineResponse2002;
import de.evangeliumstaucher.model.InlineResponse2003;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public BooksApi() {
        this(Configuration.getDefaultApiClient());
    }

    public BooksApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for getAudioBook
     * @param audioBibleId Id of audio Bible whose Book to fetch (required)
     * @param bookId Id of the Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Book not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAudioBookCall(String audioBibleId, String bookId, Boolean includeChapters, final ApiCallback _callback) throws ApiException {
        String basePath = null;

        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/v1/audio-bibles/{audioBibleId}/books/{bookId}"
            .replaceAll("\\{" + "audioBibleId" + "\\}", localVarApiClient.escapeString(audioBibleId.toString()))
            .replaceAll("\\{" + "bookId" + "\\}", localVarApiClient.escapeString(bookId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (includeChapters != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-chapters", includeChapters));
        }

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getAudioBookValidateBeforeCall(String audioBibleId, String bookId, Boolean includeChapters, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'audioBibleId' is set
        if (audioBibleId == null) {
            throw new ApiException("Missing the required parameter 'audioBibleId' when calling getAudioBook(Async)");
        }
        
        // verify the required parameter 'bookId' is set
        if (bookId == null) {
            throw new ApiException("Missing the required parameter 'bookId' when calling getAudioBook(Async)");
        }
        

        okhttp3.Call localVarCall = getAudioBookCall(audioBibleId, bookId, includeChapters, _callback);
        return localVarCall;

    }

    /**
     * 
     * Gets a single &#x60;Book&#x60; object for a given &#x60;audioBibleId&#x60; and &#x60;bookId&#x60; 
     * @param audioBibleId Id of audio Bible whose Book to fetch (required)
     * @param bookId Id of the Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @return InlineResponse2003
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Book not found </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse2003 getAudioBook(String audioBibleId, String bookId, Boolean includeChapters) throws ApiException {
        ApiResponse<InlineResponse2003> localVarResp = getAudioBookWithHttpInfo(audioBibleId, bookId, includeChapters);
        return localVarResp.getData();
    }

    /**
     * 
     * Gets a single &#x60;Book&#x60; object for a given &#x60;audioBibleId&#x60; and &#x60;bookId&#x60; 
     * @param audioBibleId Id of audio Bible whose Book to fetch (required)
     * @param bookId Id of the Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @return ApiResponse&lt;InlineResponse2003&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Book not found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse2003> getAudioBookWithHttpInfo(String audioBibleId, String bookId, Boolean includeChapters) throws ApiException {
        okhttp3.Call localVarCall = getAudioBookValidateBeforeCall(audioBibleId, bookId, includeChapters, null);
        Type localVarReturnType = new TypeToken<InlineResponse2003>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Gets a single &#x60;Book&#x60; object for a given &#x60;audioBibleId&#x60; and &#x60;bookId&#x60; 
     * @param audioBibleId Id of audio Bible whose Book to fetch (required)
     * @param bookId Id of the Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Book not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAudioBookAsync(String audioBibleId, String bookId, Boolean includeChapters, final ApiCallback<InlineResponse2003> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAudioBookValidateBeforeCall(audioBibleId, bookId, includeChapters, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse2003>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getAudioBooks
     * @param audioBibleId Id of audio Bible whose Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param includeChaptersAndSections Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAudioBooksCall(String audioBibleId, Boolean includeChapters, Boolean includeChaptersAndSections, final ApiCallback _callback) throws ApiException {
        String basePath = null;

        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/v1/audio-bibles/{audioBibleId}/books"
            .replaceAll("\\{" + "audioBibleId" + "\\}", localVarApiClient.escapeString(audioBibleId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (includeChapters != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-chapters", includeChapters));
        }

        if (includeChaptersAndSections != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-chapters-and-sections", includeChaptersAndSections));
        }

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getAudioBooksValidateBeforeCall(String audioBibleId, Boolean includeChapters, Boolean includeChaptersAndSections, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'audioBibleId' is set
        if (audioBibleId == null) {
            throw new ApiException("Missing the required parameter 'audioBibleId' when calling getAudioBooks(Async)");
        }
        

        okhttp3.Call localVarCall = getAudioBooksCall(audioBibleId, includeChapters, includeChaptersAndSections, _callback);
        return localVarCall;

    }

    /**
     * 
     * Gets an array of &#x60;Book&#x60; objects for a given &#x60;audioBibleId&#x60; 
     * @param audioBibleId Id of audio Bible whose Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param includeChaptersAndSections Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false.  (optional)
     * @return InlineResponse2002
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse2002 getAudioBooks(String audioBibleId, Boolean includeChapters, Boolean includeChaptersAndSections) throws ApiException {
        ApiResponse<InlineResponse2002> localVarResp = getAudioBooksWithHttpInfo(audioBibleId, includeChapters, includeChaptersAndSections);
        return localVarResp.getData();
    }

    /**
     * 
     * Gets an array of &#x60;Book&#x60; objects for a given &#x60;audioBibleId&#x60; 
     * @param audioBibleId Id of audio Bible whose Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param includeChaptersAndSections Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false.  (optional)
     * @return ApiResponse&lt;InlineResponse2002&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse2002> getAudioBooksWithHttpInfo(String audioBibleId, Boolean includeChapters, Boolean includeChaptersAndSections) throws ApiException {
        okhttp3.Call localVarCall = getAudioBooksValidateBeforeCall(audioBibleId, includeChapters, includeChaptersAndSections, null);
        Type localVarReturnType = new TypeToken<InlineResponse2002>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Gets an array of &#x60;Book&#x60; objects for a given &#x60;audioBibleId&#x60; 
     * @param audioBibleId Id of audio Bible whose Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param includeChaptersAndSections Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAudioBooksAsync(String audioBibleId, Boolean includeChapters, Boolean includeChaptersAndSections, final ApiCallback<InlineResponse2002> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAudioBooksValidateBeforeCall(audioBibleId, includeChapters, includeChaptersAndSections, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse2002>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getBook
     * @param bibleId Id of Bible whose Book to fetch (required)
     * @param bookId Id of the Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Book not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getBookCall(String bibleId, String bookId, Boolean includeChapters, final ApiCallback _callback) throws ApiException {
        String basePath = null;

        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/v1/bibles/{bibleId}/books/{bookId}"
            .replaceAll("\\{" + "bibleId" + "\\}", localVarApiClient.escapeString(bibleId.toString()))
            .replaceAll("\\{" + "bookId" + "\\}", localVarApiClient.escapeString(bookId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (includeChapters != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-chapters", includeChapters));
        }

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getBookValidateBeforeCall(String bibleId, String bookId, Boolean includeChapters, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'bibleId' is set
        if (bibleId == null) {
            throw new ApiException("Missing the required parameter 'bibleId' when calling getBook(Async)");
        }
        
        // verify the required parameter 'bookId' is set
        if (bookId == null) {
            throw new ApiException("Missing the required parameter 'bookId' when calling getBook(Async)");
        }
        

        okhttp3.Call localVarCall = getBookCall(bibleId, bookId, includeChapters, _callback);
        return localVarCall;

    }

    /**
     * 
     * Gets a single &#x60;Book&#x60; object for a given &#x60;bibleId&#x60; and &#x60;bookId&#x60; 
     * @param bibleId Id of Bible whose Book to fetch (required)
     * @param bookId Id of the Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @return InlineResponse2003
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Book not found </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse2003 getBook(String bibleId, String bookId, Boolean includeChapters) throws ApiException {
        ApiResponse<InlineResponse2003> localVarResp = getBookWithHttpInfo(bibleId, bookId, includeChapters);
        return localVarResp.getData();
    }

    /**
     * 
     * Gets a single &#x60;Book&#x60; object for a given &#x60;bibleId&#x60; and &#x60;bookId&#x60; 
     * @param bibleId Id of Bible whose Book to fetch (required)
     * @param bookId Id of the Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @return ApiResponse&lt;InlineResponse2003&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Book not found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse2003> getBookWithHttpInfo(String bibleId, String bookId, Boolean includeChapters) throws ApiException {
        okhttp3.Call localVarCall = getBookValidateBeforeCall(bibleId, bookId, includeChapters, null);
        Type localVarReturnType = new TypeToken<InlineResponse2003>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Gets a single &#x60;Book&#x60; object for a given &#x60;bibleId&#x60; and &#x60;bookId&#x60; 
     * @param bibleId Id of Bible whose Book to fetch (required)
     * @param bookId Id of the Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Book not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getBookAsync(String bibleId, String bookId, Boolean includeChapters, final ApiCallback<InlineResponse2003> _callback) throws ApiException {

        okhttp3.Call localVarCall = getBookValidateBeforeCall(bibleId, bookId, includeChapters, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse2003>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getBooks
     * @param bibleId Id of Bible whose Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param includeChaptersAndSections Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false.  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getBooksCall(String bibleId, Boolean includeChapters, Boolean includeChaptersAndSections, final ApiCallback _callback) throws ApiException {
        String basePath = null;

        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/v1/bibles/{bibleId}/books"
            .replaceAll("\\{" + "bibleId" + "\\}", localVarApiClient.escapeString(bibleId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (includeChapters != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-chapters", includeChapters));
        }

        if (includeChaptersAndSections != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-chapters-and-sections", includeChaptersAndSections));
        }

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "ApiKeyAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getBooksValidateBeforeCall(String bibleId, Boolean includeChapters, Boolean includeChaptersAndSections, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'bibleId' is set
        if (bibleId == null) {
            throw new ApiException("Missing the required parameter 'bibleId' when calling getBooks(Async)");
        }
        

        okhttp3.Call localVarCall = getBooksCall(bibleId, includeChapters, includeChaptersAndSections, _callback);
        return localVarCall;

    }

    /**
     * 
     * Gets an array of &#x60;Book&#x60; objects for a given &#x60;bibleId&#x60; 
     * @param bibleId Id of Bible whose Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param includeChaptersAndSections Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false.  (optional)
     * @return InlineResponse2002
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse2002 getBooks(String bibleId, Boolean includeChapters, Boolean includeChaptersAndSections) throws ApiException {
        ApiResponse<InlineResponse2002> localVarResp = getBooksWithHttpInfo(bibleId, includeChapters, includeChaptersAndSections);
        return localVarResp.getData();
    }

    /**
     * 
     * Gets an array of &#x60;Book&#x60; objects for a given &#x60;bibleId&#x60; 
     * @param bibleId Id of Bible whose Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param includeChaptersAndSections Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false.  (optional)
     * @return ApiResponse&lt;InlineResponse2002&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse2002> getBooksWithHttpInfo(String bibleId, Boolean includeChapters, Boolean includeChaptersAndSections) throws ApiException {
        okhttp3.Call localVarCall = getBooksValidateBeforeCall(bibleId, includeChapters, includeChaptersAndSections, null);
        Type localVarReturnType = new TypeToken<InlineResponse2002>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Gets an array of &#x60;Book&#x60; objects for a given &#x60;bibleId&#x60; 
     * @param bibleId Id of Bible whose Book to fetch (required)
     * @param includeChapters Boolean indicating if an array of chapter summaries should be included in the results. Defaults to false.  (optional)
     * @param includeChaptersAndSections Boolean indicating if an array of chapter summaries and an array of sections should be included in the results. Defaults to false.  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve Books for this Bible </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getBooksAsync(String bibleId, Boolean includeChapters, Boolean includeChaptersAndSections, final ApiCallback<InlineResponse2002> _callback) throws ApiException {

        okhttp3.Call localVarCall = getBooksValidateBeforeCall(bibleId, includeChapters, includeChaptersAndSections, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse2002>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
