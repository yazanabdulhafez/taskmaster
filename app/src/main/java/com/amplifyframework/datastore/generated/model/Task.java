package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Task type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Tasks", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byTeam", fields = {"teamID"})
public final class Task implements Model {
  public static final QueryField ID = field("Task", "id");
  public static final QueryField TITLE = field("Task", "title");
  public static final QueryField BODY = field("Task", "body");
  public static final QueryField STATE = field("Task", "state");
  public static final QueryField FILEKEY = field("Task", "filekey");
  public static final QueryField ALTITUDE = field("Task", "altitude");
  public static final QueryField LONGITUDE = field("Task", "longitude");
  public static final QueryField TEAM_ID = field("Task", "teamID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String", isRequired = true) String body;
  private final @ModelField(targetType="String", isRequired = true) String state;
  private final @ModelField(targetType="String", isRequired = true) String filekey;
  private final @ModelField(targetType="Float", isRequired = true) Double altitude;
  private final @ModelField(targetType="Float", isRequired = true) Double longitude;
  private final @ModelField(targetType="ID") String teamID;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getBody() {
      return body;
  }
  
  public String getState() {
      return state;
  }
  
  public String getFilekey() {
      return filekey;
  }
  
  public Double getAltitude() {
      return altitude;
  }
  
  public Double getLongitude() {
      return longitude;
  }
  
  public String getTeamId() {
      return teamID;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Task(String id, String title, String body, String state, String filekey, Double altitude, Double longitude, String teamID) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.state = state;
    this.filekey = filekey;
    this.altitude = altitude;
    this.longitude = longitude;
    this.teamID = teamID;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Task task = (Task) obj;
      return ObjectsCompat.equals(getId(), task.getId()) &&
              ObjectsCompat.equals(getTitle(), task.getTitle()) &&
              ObjectsCompat.equals(getBody(), task.getBody()) &&
              ObjectsCompat.equals(getState(), task.getState()) &&
              ObjectsCompat.equals(getFilekey(), task.getFilekey()) &&
              ObjectsCompat.equals(getAltitude(), task.getAltitude()) &&
              ObjectsCompat.equals(getLongitude(), task.getLongitude()) &&
              ObjectsCompat.equals(getTeamId(), task.getTeamId()) &&
              ObjectsCompat.equals(getCreatedAt(), task.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), task.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getBody())
      .append(getState())
      .append(getFilekey())
      .append(getAltitude())
      .append(getLongitude())
      .append(getTeamId())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Task {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("body=" + String.valueOf(getBody()) + ", ")
      .append("state=" + String.valueOf(getState()) + ", ")
      .append("filekey=" + String.valueOf(getFilekey()) + ", ")
      .append("altitude=" + String.valueOf(getAltitude()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
      .append("teamID=" + String.valueOf(getTeamId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TitleStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Task justId(String id) {
    return new Task(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      body,
      state,
      filekey,
      altitude,
      longitude,
      teamID);
  }
  public interface TitleStep {
    BodyStep title(String title);
  }
  

  public interface BodyStep {
    StateStep body(String body);
  }
  

  public interface StateStep {
    FilekeyStep state(String state);
  }
  

  public interface FilekeyStep {
    AltitudeStep filekey(String filekey);
  }
  

  public interface AltitudeStep {
    LongitudeStep altitude(Double altitude);
  }
  

  public interface LongitudeStep {
    BuildStep longitude(Double longitude);
  }
  

  public interface BuildStep {
    Task build();
    BuildStep id(String id);
    BuildStep teamId(String teamId);
  }
  

  public static class Builder implements TitleStep, BodyStep, StateStep, FilekeyStep, AltitudeStep, LongitudeStep, BuildStep {
    private String id;
    private String title;
    private String body;
    private String state;
    private String filekey;
    private Double altitude;
    private Double longitude;
    private String teamID;
    @Override
     public Task build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Task(
          id,
          title,
          body,
          state,
          filekey,
          altitude,
          longitude,
          teamID);
    }
    
    @Override
     public BodyStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public StateStep body(String body) {
        Objects.requireNonNull(body);
        this.body = body;
        return this;
    }
    
    @Override
     public FilekeyStep state(String state) {
        Objects.requireNonNull(state);
        this.state = state;
        return this;
    }
    
    @Override
     public AltitudeStep filekey(String filekey) {
        Objects.requireNonNull(filekey);
        this.filekey = filekey;
        return this;
    }
    
    @Override
     public LongitudeStep altitude(Double altitude) {
        Objects.requireNonNull(altitude);
        this.altitude = altitude;
        return this;
    }
    
    @Override
     public BuildStep longitude(Double longitude) {
        Objects.requireNonNull(longitude);
        this.longitude = longitude;
        return this;
    }
    
    @Override
     public BuildStep teamId(String teamId) {
        this.teamID = teamId;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String title, String body, String state, String filekey, Double altitude, Double longitude, String teamId) {
      super.id(id);
      super.title(title)
        .body(body)
        .state(state)
        .filekey(filekey)
        .altitude(altitude)
        .longitude(longitude)
        .teamId(teamId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder body(String body) {
      return (CopyOfBuilder) super.body(body);
    }
    
    @Override
     public CopyOfBuilder state(String state) {
      return (CopyOfBuilder) super.state(state);
    }
    
    @Override
     public CopyOfBuilder filekey(String filekey) {
      return (CopyOfBuilder) super.filekey(filekey);
    }
    
    @Override
     public CopyOfBuilder altitude(Double altitude) {
      return (CopyOfBuilder) super.altitude(altitude);
    }
    
    @Override
     public CopyOfBuilder longitude(Double longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
    
    @Override
     public CopyOfBuilder teamId(String teamId) {
      return (CopyOfBuilder) super.teamId(teamId);
    }
  }
  
}
