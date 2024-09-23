function fetchDepartments() {
    return fetch("http://localhost:8080/api/departments")
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        });
}


document.querySelectorAll('.view-employee, .edit-employee, .delete-employee').forEach(button => {
    button.addEventListener('click', function () {
        const actionId = this.getAttribute('data-action-id');
        const actionType = this.classList.contains('view-employee') ? 'view' :
            this.classList.contains('edit-employee') ? 'edit' : 'delete';

        console.log(`Action: ${actionType}, ID: ${actionId}`);

        if (actionType === 'view') {
            fetch(`/api/employees/${actionId}`, {
                method: 'GET',
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    document.getElementById('first-name-view').value = data.firstName;
                    document.getElementById('last-name-view').value = data.lastName;
                    document.getElementById('dob-view').value = data.dob.split('T')[0];
                    document.getElementById('address-view').value = data.address;
                    document.getElementById('phone-view').value = data.phone;
                    document.getElementById('department-view').value = data.department.name;
                })
                .catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });
        } else if (actionType === 'edit') {
            fetchDepartments().then(departments => {
                return fetch(`/api/employees/${actionId}`, {
                    method: 'GET',
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.json();
                    })
                    .then(data => {
                        document.getElementById('first-name-edit').value = data.firstName;
                        document.getElementById('last-name-edit').value = data.lastName;
                        document.getElementById('dob-edit').value = data.dob.split('T')[0];
                        document.getElementById('address-edit').value = data.address;
                        document.getElementById('phone-edit').value = data.phone;
                        const departmentSelect = document.getElementById('department-edit');
                        departmentSelect.innerHTML = '';
                        departments.forEach(department => {
                            const option = document.createElement('option');
                            option.value = department.id;
                            option.textContent = department.name;
                            departmentSelect.appendChild(option);
                        });
                        departmentSelect.value = data.department.id;
                        document.querySelector("#edit-form").addEventListener('submit', function(e) {
                            e.preventDefault();
                            const departmentSelect = document.getElementById('department-edit');
                            const selectedOption = departmentSelect.options[departmentSelect.selectedIndex];  //using to get value like textContent
                            const updatedData = {
                                firstName: document.getElementById('first-name-edit').value,
                                lastName: document.getElementById('last-name-edit').value,
                                dob: document.getElementById('dob-edit').value,
                                address: document.getElementById('address-edit').value,
                                phone: document.getElementById('phone-edit').value,
                                department: {
                                    id: selectedOption.value,
                                    name: selectedOption.textContent
                                }
                            };

                            fetch(`api/employees/${actionId}`, {
                                method: 'PUT',
                                headers: {
                                    'Content-Type': 'application/json',
                                },
                                body: JSON.stringify(updatedData),
                            }).then(response => {
                                if (!response.ok) {
                                    throw new Error('Network response was not ok');
                                }
                                console.log("Update successful:", response);
                                location.reload();
                            })
                                .catch(error => {
                                    console.error('There was a problem with the update operation:', error);
                                });
                        });
                    })
                    .catch(error => {
                        console.error('There was a problem with the fetch operation:', error);
                    });
            })

        }
    });
});
