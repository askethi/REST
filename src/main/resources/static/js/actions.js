const rolesSelected = document.getElementById("roles");



function getRoles(rolesSelected) {
    let roles = [];
    for (let option of rolesSelected.selectedOptions) {
        roles.push({id: option.index, role: option.value});
    }
    return roles;
}

async function createNewUser(user) {
    await fetch("/api/users",
        {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(user)})
}

async function sendDataEditUser(user) {
    await fetch("/api/users" ,
        {method:"PUT", headers: {'Content-type': 'application/json'}, body: JSON.stringify(user)} )
}

async function deleteUserData(userId){
    await fetch(`/api/users/${userId}`, {method: 'DELETE'});
}

const modalEdit = document.getElementById("editModal");
const modalDelete = document.getElementById("deleteModal");

async function EditModalHandler() {
    await fillModal(modalEdit);
}

async function DeleteModalHandler() {
    await fillModal(modalDelete);
}

modalDelete.addEventListener("submit", async function(event) {
        event.preventDefault();
        const userId = event.target.querySelector("#idDelete").value;
        await deleteUserData(userId);
        await fillTableOfAllUsers();
        const modalBootstrap = bootstrap.Modal.getInstance(modalDelete);
        modalBootstrap.hide();
    }
)

modalEdit.addEventListener("submit", async function(event){
    event.preventDefault();

    let user = {
        id: document.getElementById("idEdit").value,
        firstName: document.getElementById("firstNameEdit").value,
        lastName: document.getElementById("lastNameEdit").value,
        password: document.getElementById("passwordEdit").value,
        username: document.getElementById("emailEdit").value,
        email: document.getElementById("emailEdit").value,
        age: document.getElementById("ageEdit").value,
        roles: getRoles(document.getElementById("rolesEdit"))
    }
    await sendDataEditUser(user);
    await fillTableOfAllUsers();
    const modalBootstrap = bootstrap.Modal.getInstance(modalEdit);
    modalBootstrap.hide();
})

async function addNewUserForm() {
    const newUserForm = document.getElementById("newUser");

    newUserForm.addEventListener('submit', async function (event) {
        event.preventDefault();

        const firstName = newUserForm.querySelector("#firstName").value.trim();
        const lastName = newUserForm.querySelector("#lastName").value.trim();
        const password = newUserForm.querySelector("#password").value.trim();
        const email = newUserForm.querySelector("#email").value.trim();
        const age = newUserForm.querySelector("#age").value.trim();

        const newUserData = {
            firstName: firstName,
            lastName: lastName,
            password: password,
            email: email,
            age: age,
            roles: getRoles(document.getElementById("roles"))
        };
        await createNewUser(newUserData);
        newUserForm.reset();
        document.querySelector('a#show-users-table').click();
        await fillTableOfAllUsers();
    });
}