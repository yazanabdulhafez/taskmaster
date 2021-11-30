# taskmaster

## Overview

This is an application used to mange the tasks.

## Lab 26

### Feature Tasks

#### Homepage

The main page should have a heading at the top of the page, an image to mock the “my tasks” view, and buttons at the bottom of the page to allow going to the “add tasks” and “all tasks” page.

![Home-page](screenshots/1.png)

#### Add a Task

On the “Add a Task” page, allow users to type in details about a new task, specifically a title and a body. When users click the “submit” button, show a “submitted!” label on the page.

![Ad-Task-page](screenshots/2.png)

#### All Tasks

The all tasks page should just be an image with a back button; it needs no functionality.

![All-Tasks-page](screenshots/3.png)

## Lab 27

### Feature Tasks for this lab

#### Task Detail Page

Task Detail page should have a title at the top of the page, and a Lorem Ipsum description.

![task detail page](screenshots/27t.png)

#### Settings Page

Settings page should allow users to enter their username and hit save.

![Setting page](screenshots/27s.png)

#### Homepage(1)

The main page should be modified to contain three different buttons with hardcoded task titles. When a user taps one of the titles, it should go to the Task Detail page, and the title at the top of the page should match the task title that was tapped on the previous page.

The homepage should also contain a button to visit the Settings page, and once the user has entered their username, it should display “{username}’s tasks” above the three task buttons

![new home page](screenshots/27h1.png)

## Lab 28

### Feature Tasks for lab 28

#### Task Model

Create a Task class. A Task should have a title, a body, and a state. The state should be one of “new”, “assigned”, “in progress”, or “complete”.

[Task Model](app/src/main/java/com/example/taskmaster/Models/Task.java)

#### Homepage for lab 28

Refactor your homepage to use a RecyclerView for displaying Task data. This should have hardcoded Task data for now.

Some steps you will likely want to take to accomplish this:

Create a ViewAdapter class that displays data from a list of Tasks.
In your MainActivity, create at least three hardcoded Task instances and use those to populate your RecyclerView/ViewAdapter.
Ensure that you can tap on any one of the Tasks in the RecyclerView, and it will appropriately launch the detail page with the correct Task title displayed.

![new home page](screenshots/281.png)

## Lab 29

### Feature Tasks for lab 29

#### Task Model and Room

Following the directions provided in the Android documentation, set up Room in your application, and modify your Task class to be an Entity.

[Task Model](app/src/main/java/com/example/taskmaster/Models/Task.java)

#### Add Task Form

Modify your Add Task form to save the data entered in as a Task in your local database.

![Add task](screenshots/29at.png)

#### Homepage new

Refactor your homepage’s RecyclerView to display all Task entities in your database.

#### Detail Page new

Ensure that the description and status of a tapped task are also displayed on the detail page, in addition to the title. (Note that you can accomplish this by passing along the entire Task entity, or by passing along only its ID in the intent.)

![task detail](screenshots/29d.png)

## Lab 32

### Feature Tasks for lab 32

#### Tasks Are Cloudy

Using the amplify add api command, create a Task resource that replicates our existing Task schema. Update all references to the Task data to instead use AWS Amplify to access your data in DynamoDB instead of in Room.

#### Add Task Form edited

Modify your Add Task form to save the data entered in as a Task to DynamoDB.

#### Homepage edited

Refactor your homepage’s RecyclerView to display all Task entities in DynamoDB.

## Lab 33

### Feature Tasks for lab 33

#### Tasks Are Owned By Teams

Create a second entity for a team, which has a name and a list of tasks. Update your tasks to be owned by a team.

Manually create three teams by running a mutation exactly three times in your code. (You do NOT need to allow the user to create new teams.)

![home page](/screenshots/lab33hp.png)

#### Add Task Form(edit)

Modify your Add Task form to include either a Spinner or Radio Buttons for which team that task belongs to.

![task form](/screenshots/lab33ar.png)

#### Settings Page(edit)

In addition to a username, allow the user to choose their team on the Settings page. Use that Team to display only that team’s tasks on the homepage.

![setting page](/screenshots/lab33set.png)

## Lab 36

### Feature Tasks for lab 36

#### User Login

Add Cognito to your Amplify setup. Add in user login and sign up flows to your application, using Cognito’s pre-built UI as appropriate. Display the logged in user’s username somewhere relevant in your app.

Sign UP

![sign up](/screenshots/lab36signup.png)

Sign In

![sign in](/screenshots/lab36signin.png)

Main Page

![main page](/screenshots/lab36m.png)

#### User Logout

Allow users to log out of your application.

## Lab 37

### Feature Tasks for lab 37

#### Uploads

On the “Add a Task” activity, allow users to optionally select a file to attach to that task. If a user attaches a file to a task, that file should be uploaded to S3, and associated with that task.

![add task](/screenshots/lab37add.png)

#### Displaying Files

On the Task detail activity, if there is a file that is an image associated with a particular Task, that image should be displayed within that activity. (If the file is any other type, you should display a link to it.)

![task details](/screenshots/lab37td.png)

![home page](screenshots/lab37main.png)