# SustainableApps
Project to test Espresso and JUnit with a basic login screen applying MVP as a architecture pattern
# Requirements 
  - The LoginActivity must have a place for the user to enter their email address
  - The LoginActivity must have a place for the user to enter their password
  - The LoginActivity must not display the password in plain text
  - The LoginActivity must have a login button that displays the text "Login"
  - The LoginActivity must have a way to display an error message to the user
  - When the login button is clicked, the activity should check that the password is at least 8
    characters long and display the error message "The provided password is too short" if the
    password is too short
  - When the login button is clicked, the activity should check that the password is not all spaces
    and display the error message "The provided password is invalid" if the password is all spaces
  - When the login button is clicked, the activity should check that the email address: a) has an
    "@" sign, b) has at least 1 character before the "@" sign, and c) has at least 3 characters,
    including a "." after the "@" sign
  - If any of the rules from #8 are violated, the LoginActivity should display the error message
    "Invalid email address"
  - The LoginActivity must have a way to display a success message (can be the same
    mechanism used to display the error message)
  - If the email address and password are valid, the success message "Login success" should be
    displayed
