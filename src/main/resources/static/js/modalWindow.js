
async function fillModal(modal) {
    modal.addEventListener("show.bs.modal", async function (event) {

        const userId = event.relatedTarget.dataset.userId;
        const user = await fetch(`/api/users/${userId}`).then(u => u.json());
        const modalBody = modal.querySelector(".modal-body");

        modalBody.querySelector("input[data-user-id='id']").value = user.id;
        modalBody.querySelector("input[data-user-id='firstName']").value = user.firstName;
        modalBody.querySelector("input[data-user-id='lastName']").value = user.lastName;
        if (modal === modalEdit) {
            modalBody.querySelector("input[data-user-id='password']").value = user.password;
        }
        modalBody.querySelector("input[data-user-id='email']").value = user.email;
        modalBody.querySelector("input[data-user-id='age']").value = user.age;

        if (modal === modalDelete) {
            let rolesSelect = modalBody.querySelector("select[data-user-id='rolesDelete']");
            rolesSelect.innerHTML = "";
            for (let role of user.roles) {
                rolesSelect.innerHTML +=
                    `<option value="${role.role}">${role.role
                        .substring(5).concat(" ").toString().replaceAll(",", "")}</option>`;
            }
        }
    });
}