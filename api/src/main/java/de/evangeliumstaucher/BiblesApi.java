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


import de.evangeliumstaucher.model.InlineResponse200;
import de.evangeliumstaucher.model.InlineResponse2001;
import de.evangeliumstaucher.model.InlineResponse20012;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiblesApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public BiblesApi() {
        this(Configuration.getDefaultApiClient());
    }

    public BiblesApi(ApiClient apiClient) {
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
     * Build call for getAudioBible
     * @param audioBibleId Id of audio Bible to be fetched (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bible not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAudioBibleCall(String audioBibleId, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/v1/audio-bibles/{audioBibleId}"
            .replaceAll("\\{" + "audioBibleId" + "\\}", localVarApiClient.escapeString(audioBibleId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

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
    private okhttp3.Call getAudioBibleValidateBeforeCall(String audioBibleId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'audioBibleId' is set
        if (audioBibleId == null) {
            throw new ApiException("Missing the required parameter 'audioBibleId' when calling getAudioBible(Async)");
        }
        

        okhttp3.Call localVarCall = getAudioBibleCall(audioBibleId, _callback);
        return localVarCall;

    }

    /**
     * 
     * Gets a single audio &#x60;Bible&#x60; for a given &#x60;audioBibleId&#x60; 
     * @param audioBibleId Id of audio Bible to be fetched (required)
     * @return InlineResponse20012
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bible not found </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse20012 getAudioBible(String audioBibleId) throws ApiException {
        ApiResponse<InlineResponse20012> localVarResp = getAudioBibleWithHttpInfo(audioBibleId);
        return localVarResp.getData();
    }

    /**
     * 
     * Gets a single audio &#x60;Bible&#x60; for a given &#x60;audioBibleId&#x60; 
     * @param audioBibleId Id of audio Bible to be fetched (required)
     * @return ApiResponse&lt;InlineResponse20012&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bible not found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse20012> getAudioBibleWithHttpInfo(String audioBibleId) throws ApiException {
        okhttp3.Call localVarCall = getAudioBibleValidateBeforeCall(audioBibleId, null);
        Type localVarReturnType = new TypeToken<InlineResponse20012>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Gets a single audio &#x60;Bible&#x60; for a given &#x60;audioBibleId&#x60; 
     * @param audioBibleId Id of audio Bible to be fetched (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bible not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAudioBibleAsync(String audioBibleId, final ApiCallback<InlineResponse20012> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAudioBibleValidateBeforeCall(audioBibleId, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse20012>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getAudioBibles
     * @param language ISO 639-3 three digit language code used to filter results (optional)
     * @param abbreviation Bible abbreviation to search for (optional)
     * @param name Bible name to search for (optional)
     * @param ids Comma separated list of Bible Ids to return (optional)
     * @param bibleId bibleId of related text Bible used to filter audio bible results (optional)
     * @param includeFullDetails Boolean to include full Bible details (e.g. copyright and promo info) (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Not authorized to retrieve any Bibles or invalid language_code provided </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Key provided. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAudioBiblesCall(String language, String abbreviation, String name, String ids, String bibleId, Boolean includeFullDetails, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/v1/audio-bibles";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (language != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("language", language));
        }

        if (abbreviation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("abbreviation", abbreviation));
        }

        if (name != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("name", name));
        }

        if (ids != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("ids", ids));
        }

        if (bibleId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("bibleId", bibleId));
        }

        if (includeFullDetails != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-full-details", includeFullDetails));
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
    private okhttp3.Call getAudioBiblesValidateBeforeCall(String language, String abbreviation, String name, String ids, String bibleId, Boolean includeFullDetails, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = getAudioBiblesCall(language, abbreviation, name, ids, bibleId, includeFullDetails, _callback);
        return localVarCall;

    }

    /**
     * 
     * Gets an array of audio &#x60;Bible&#x60; objects authorized for current API Key 
     * @param language ISO 639-3 three digit language code used to filter results (optional)
     * @param abbreviation Bible abbreviation to search for (optional)
     * @param name Bible name to search for (optional)
     * @param ids Comma separated list of Bible Ids to return (optional)
     * @param bibleId bibleId of related text Bible used to filter audio bible results (optional)
     * @param includeFullDetails Boolean to include full Bible details (e.g. copyright and promo info) (optional)
     * @return InlineResponse200
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Not authorized to retrieve any Bibles or invalid language_code provided </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Key provided. </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse200 getAudioBibles(String language, String abbreviation, String name, String ids, String bibleId, Boolean includeFullDetails) throws ApiException {
        ApiResponse<InlineResponse200> localVarResp = getAudioBiblesWithHttpInfo(language, abbreviation, name, ids, bibleId, includeFullDetails);
        return localVarResp.getData();
    }

    /**
     * 
     * Gets an array of audio &#x60;Bible&#x60; objects authorized for current API Key 
     * @param language ISO 639-3 three digit language code used to filter results (optional)
     * @param abbreviation Bible abbreviation to search for (optional)
     * @param name Bible name to search for (optional)
     * @param ids Comma separated list of Bible Ids to return (optional)
     * @param bibleId bibleId of related text Bible used to filter audio bible results (optional)
     * @param includeFullDetails Boolean to include full Bible details (e.g. copyright and promo info) (optional)
     * @return ApiResponse&lt;InlineResponse200&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Not authorized to retrieve any Bibles or invalid language_code provided </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Key provided. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse200> getAudioBiblesWithHttpInfo(String language, String abbreviation, String name, String ids, String bibleId, Boolean includeFullDetails) throws ApiException {
        okhttp3.Call localVarCall = getAudioBiblesValidateBeforeCall(language, abbreviation, name, ids, bibleId, includeFullDetails, null);
        Type localVarReturnType = new TypeToken<InlineResponse200>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Gets an array of audio &#x60;Bible&#x60; objects authorized for current API Key 
     * @param language ISO 639-3 three digit language code used to filter results (optional)
     * @param abbreviation Bible abbreviation to search for (optional)
     * @param name Bible name to search for (optional)
     * @param ids Comma separated list of Bible Ids to return (optional)
     * @param bibleId bibleId of related text Bible used to filter audio bible results (optional)
     * @param includeFullDetails Boolean to include full Bible details (e.g. copyright and promo info) (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Not authorized to retrieve any Bibles or invalid language_code provided </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Key provided. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getAudioBiblesAsync(String language, String abbreviation, String name, String ids, String bibleId, Boolean includeFullDetails, final ApiCallback<InlineResponse200> _callback) throws ApiException {

        okhttp3.Call localVarCall = getAudioBiblesValidateBeforeCall(language, abbreviation, name, ids, bibleId, includeFullDetails, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse200>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getBible
     * @param bibleId Id of Bible to be fetched (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bible not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getBibleCall(String bibleId, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/v1/bibles/{bibleId}"
            .replaceAll("\\{" + "bibleId" + "\\}", localVarApiClient.escapeString(bibleId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

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
    private okhttp3.Call getBibleValidateBeforeCall(String bibleId, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'bibleId' is set
        if (bibleId == null) {
            throw new ApiException("Missing the required parameter 'bibleId' when calling getBible(Async)");
        }
        

        okhttp3.Call localVarCall = getBibleCall(bibleId, _callback);
        return localVarCall;

    }

    /**
     * 
     * Gets a single &#x60;Bible&#x60; for a given &#x60;bibleId&#x60; 
     * @param bibleId Id of Bible to be fetched (required)
     * @return InlineResponse2001
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bible not found </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse2001 getBible(String bibleId) throws ApiException {
        ApiResponse<InlineResponse2001> localVarResp = getBibleWithHttpInfo(bibleId);
        return localVarResp.getData();
    }

    /**
     * 
     * Gets a single &#x60;Bible&#x60; for a given &#x60;bibleId&#x60; 
     * @param bibleId Id of Bible to be fetched (required)
     * @return ApiResponse&lt;InlineResponse2001&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bible not found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse2001> getBibleWithHttpInfo(String bibleId) throws ApiException {
        okhttp3.Call localVarCall = getBibleValidateBeforeCall(bibleId, null);
        Type localVarReturnType = new TypeToken<InlineResponse2001>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Gets a single &#x60;Bible&#x60; for a given &#x60;bibleId&#x60; 
     * @param bibleId Id of Bible to be fetched (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid ID supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Token provided. </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> Not authorized to retrieve this Bible </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bible not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getBibleAsync(String bibleId, final ApiCallback<InlineResponse2001> _callback) throws ApiException {

        okhttp3.Call localVarCall = getBibleValidateBeforeCall(bibleId, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse2001>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getBibles
     * @param language ISO 639-3 three digit language code used to filter results (optional)
     * @param abbreviation Bible abbreviation to search for (optional)
     * @param name Bible name to search for (optional)
     * @param ids Comma separated list of Bible Ids to return (optional)
     * @param includeFullDetails Boolean to include full Bible details (e.g. copyright and promo info) (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Not authorized to retrieve any Bibles or invalid language_code provided </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Key provided. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getBiblesCall(String language, String abbreviation, String name, String ids, Boolean includeFullDetails, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/v1/bibles";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (language != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("language", language));
        }

        if (abbreviation != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("abbreviation", abbreviation));
        }

        if (name != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("name", name));
        }

        if (ids != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("ids", ids));
        }

        if (includeFullDetails != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("include-full-details", includeFullDetails));
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
    private okhttp3.Call getBiblesValidateBeforeCall(String language, String abbreviation, String name, String ids, Boolean includeFullDetails, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = getBiblesCall(language, abbreviation, name, ids, includeFullDetails, _callback);
        return localVarCall;

    }

    /**
     * 
     * Gets an array of &#x60;Bible&#x60; objects authorized for current API Key 
     * @param language ISO 639-3 three digit language code used to filter results (optional)
     * @param abbreviation Bible abbreviation to search for (optional)
     * @param name Bible name to search for (optional)
     * @param ids Comma separated list of Bible Ids to return (optional)
     * @param includeFullDetails Boolean to include full Bible details (e.g. copyright and promo info) (optional)
     * @return InlineResponse200
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Not authorized to retrieve any Bibles or invalid language_code provided </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Key provided. </td><td>  -  </td></tr>
     </table>
     */
    public InlineResponse200 getBibles(String language, String abbreviation, String name, String ids, Boolean includeFullDetails) throws ApiException {
        ApiResponse<InlineResponse200> localVarResp = getBiblesWithHttpInfo(language, abbreviation, name, ids, includeFullDetails);
        return localVarResp.getData();
    }

    /**
     * 
     * Gets an array of &#x60;Bible&#x60; objects authorized for current API Key 
     * @param language ISO 639-3 three digit language code used to filter results (optional)
     * @param abbreviation Bible abbreviation to search for (optional)
     * @param name Bible name to search for (optional)
     * @param ids Comma separated list of Bible Ids to return (optional)
     * @param includeFullDetails Boolean to include full Bible details (e.g. copyright and promo info) (optional)
     * @return ApiResponse&lt;InlineResponse200&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Not authorized to retrieve any Bibles or invalid language_code provided </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Key provided. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InlineResponse200> getBiblesWithHttpInfo(String language, String abbreviation, String name, String ids, Boolean includeFullDetails) throws ApiException {
        okhttp3.Call localVarCall = getBiblesValidateBeforeCall(language, abbreviation, name, ids, includeFullDetails, null);
        Type localVarReturnType = new TypeToken<InlineResponse200>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Gets an array of &#x60;Bible&#x60; objects authorized for current API Key 
     * @param language ISO 639-3 three digit language code used to filter results (optional)
     * @param abbreviation Bible abbreviation to search for (optional)
     * @param name Bible name to search for (optional)
     * @param ids Comma separated list of Bible Ids to return (optional)
     * @param includeFullDetails Boolean to include full Bible details (e.g. copyright and promo info) (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Not authorized to retrieve any Bibles or invalid language_code provided </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized for API access.  Missing or Invalid API Key provided. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getBiblesAsync(String language, String abbreviation, String name, String ids, Boolean includeFullDetails, final ApiCallback<InlineResponse200> _callback) throws ApiException {

        okhttp3.Call localVarCall = getBiblesValidateBeforeCall(language, abbreviation, name, ids, includeFullDetails, _callback);
        Type localVarReturnType = new TypeToken<InlineResponse200>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
