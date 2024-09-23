$(document).ready(function() {
    $("#employeeForm").validate({
        rules: {
            "firstName": {
                required: true,
                minlength: 2
            },
            "lastName": {
                required: true,
                minlength: 2
            },
            "phone": {
                required: true,
                digits: true,
                minlength: 10
            },
            "dob": {
                required: true,
                date: true
            },
            "gender": {
                required: true
            },
            "accountDto.account": {
                required: true,
                minlength: 5
            },
            "accountDto.email": {
                required: true,
                email: true
            },
            "accountDto.password": {
                required: true,
                minlength: 6
            },
            "address": {
                required: true
            },
            "department": {
                required: true
            }
        },
        messages: {
            "firstName": {
                required: "Please enter your first name",
                minlength: "Your first name must be at least 2 characters long"
            },
            "lastName": {
                required: "Please enter your last name",
                minlength: "Your last name must be at least 2 characters long"
            },
            "phone": {
                required: "Please enter your phone number",
                digits: "Please enter only digits",
                minlength: "Your phone number must be at least 10 digits"
            },
            "dob": {
                required: "Please enter your date of birth",
                date: "Please enter a valid date"
            },
            "gender": {
                required: "Please select your gender"
            },
            "accountDto.account": {
                required: "Please enter an account",
                minlength: "Your account must be at least 5 characters long"
            },
            "accountDto.email": {
                required: "Please enter your email",
                email: "Please enter a valid email address"
            },
            "accountDto.password": {
                required: "Please provide a password",
                minlength: "Your password must be at least 6 characters long"
            },
            "address": {
                required: "Please enter your address"
            },
            "department": {
                required: "Please select a department"
            }
        }
    });
});