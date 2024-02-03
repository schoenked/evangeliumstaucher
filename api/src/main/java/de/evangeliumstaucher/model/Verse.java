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


package de.evangeliumstaucher.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import de.evangeliumstaucher.model.VerseNext;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Verse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-02-01T23:51:10.720725+01:00[Europe/Berlin]")
public class Verse {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private String id;

  public static final String SERIALIZED_NAME_ORG_ID = "orgId";
  @SerializedName(SERIALIZED_NAME_ORG_ID)
  private String orgId;

  public static final String SERIALIZED_NAME_BIBLE_ID = "bibleId";
  @SerializedName(SERIALIZED_NAME_BIBLE_ID)
  private String bibleId;

  public static final String SERIALIZED_NAME_BOOK_ID = "bookId";
  @SerializedName(SERIALIZED_NAME_BOOK_ID)
  private String bookId;

  public static final String SERIALIZED_NAME_CHAPTER_ID = "chapterId";
  @SerializedName(SERIALIZED_NAME_CHAPTER_ID)
  private String chapterId;

  public static final String SERIALIZED_NAME_CONTENT = "content";
  @SerializedName(SERIALIZED_NAME_CONTENT)
  private String content;

  public static final String SERIALIZED_NAME_REFERENCE = "reference";
  @SerializedName(SERIALIZED_NAME_REFERENCE)
  private String reference;

  public static final String SERIALIZED_NAME_VERSE_COUNT = "verseCount";
  @SerializedName(SERIALIZED_NAME_VERSE_COUNT)
  private Integer verseCount;

  public static final String SERIALIZED_NAME_COPYRIGHT = "copyright";
  @SerializedName(SERIALIZED_NAME_COPYRIGHT)
  private String copyright;

  public static final String SERIALIZED_NAME_NEXT = "next";
  @SerializedName(SERIALIZED_NAME_NEXT)
  private VerseNext next;

  public static final String SERIALIZED_NAME_PREVIOUS = "previous";
  @SerializedName(SERIALIZED_NAME_PREVIOUS)
  private VerseNext previous;

  public Verse() { 
  }

  public Verse id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public Verse orgId(String orgId) {
    
    this.orgId = orgId;
    return this;
  }

   /**
   * Get orgId
   * @return orgId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getOrgId() {
    return orgId;
  }


  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }


  public Verse bibleId(String bibleId) {
    
    this.bibleId = bibleId;
    return this;
  }

   /**
   * Get bibleId
   * @return bibleId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getBibleId() {
    return bibleId;
  }


  public void setBibleId(String bibleId) {
    this.bibleId = bibleId;
  }


  public Verse bookId(String bookId) {
    
    this.bookId = bookId;
    return this;
  }

   /**
   * Get bookId
   * @return bookId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getBookId() {
    return bookId;
  }


  public void setBookId(String bookId) {
    this.bookId = bookId;
  }


  public Verse chapterId(String chapterId) {
    
    this.chapterId = chapterId;
    return this;
  }

   /**
   * Get chapterId
   * @return chapterId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getChapterId() {
    return chapterId;
  }


  public void setChapterId(String chapterId) {
    this.chapterId = chapterId;
  }


  public Verse content(String content) {
    
    this.content = content;
    return this;
  }

   /**
   * Get content
   * @return content
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getContent() {
    return content;
  }


  public void setContent(String content) {
    this.content = content;
  }


  public Verse reference(String reference) {
    
    this.reference = reference;
    return this;
  }

   /**
   * Get reference
   * @return reference
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getReference() {
    return reference;
  }


  public void setReference(String reference) {
    this.reference = reference;
  }


  public Verse verseCount(Integer verseCount) {
    
    this.verseCount = verseCount;
    return this;
  }

   /**
   * Get verseCount
   * @return verseCount
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Integer getVerseCount() {
    return verseCount;
  }


  public void setVerseCount(Integer verseCount) {
    this.verseCount = verseCount;
  }


  public Verse copyright(String copyright) {
    
    this.copyright = copyright;
    return this;
  }

   /**
   * Get copyright
   * @return copyright
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getCopyright() {
    return copyright;
  }


  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }


  public Verse next(VerseNext next) {
    
    this.next = next;
    return this;
  }

   /**
   * Get next
   * @return next
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public VerseNext getNext() {
    return next;
  }


  public void setNext(VerseNext next) {
    this.next = next;
  }


  public Verse previous(VerseNext previous) {
    
    this.previous = previous;
    return this;
  }

   /**
   * Get previous
   * @return previous
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public VerseNext getPrevious() {
    return previous;
  }


  public void setPrevious(VerseNext previous) {
    this.previous = previous;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Verse verse = (Verse) o;
    return Objects.equals(this.id, verse.id) &&
        Objects.equals(this.orgId, verse.orgId) &&
        Objects.equals(this.bibleId, verse.bibleId) &&
        Objects.equals(this.bookId, verse.bookId) &&
        Objects.equals(this.chapterId, verse.chapterId) &&
        Objects.equals(this.content, verse.content) &&
        Objects.equals(this.reference, verse.reference) &&
        Objects.equals(this.verseCount, verse.verseCount) &&
        Objects.equals(this.copyright, verse.copyright) &&
        Objects.equals(this.next, verse.next) &&
        Objects.equals(this.previous, verse.previous);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orgId, bibleId, bookId, chapterId, content, reference, verseCount, copyright, next, previous);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Verse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    orgId: ").append(toIndentedString(orgId)).append("\n");
    sb.append("    bibleId: ").append(toIndentedString(bibleId)).append("\n");
    sb.append("    bookId: ").append(toIndentedString(bookId)).append("\n");
    sb.append("    chapterId: ").append(toIndentedString(chapterId)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
    sb.append("    verseCount: ").append(toIndentedString(verseCount)).append("\n");
    sb.append("    copyright: ").append(toIndentedString(copyright)).append("\n");
    sb.append("    next: ").append(toIndentedString(next)).append("\n");
    sb.append("    previous: ").append(toIndentedString(previous)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

