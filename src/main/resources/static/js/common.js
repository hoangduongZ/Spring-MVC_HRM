document.querySelectorAll(".edit-employee").forEach(button => {
    button.addEventListener("click", function () {
        openModalEdit();
    });
});

function openModalEdit() {
    const modal = document.querySelector(".modal-edit");
    modal.classList.remove("hidden");
    modal.classList.add("block");
}

document.querySelectorAll(".view-employee").forEach(button => {
    button.addEventListener("click", function () {
        openModalView();
    });
});

function openModalView() {
    const modal = document.querySelector(".modal-view");
    modal.classList.remove("hidden");
    modal.classList.add("block");
}

document.querySelectorAll(".delete-employee").forEach(button => {
    button.addEventListener("click", function () {
        openModalDelete(button);
    });
});

function openModalDelete(button) {
    const modal = button.closest('tr').querySelector(".modal-delete");
    modal.classList.remove("hidden");
    modal.classList.add("block");

}

document.querySelectorAll(".close").forEach(button => {
    button.addEventListener("click", function () {
        closeModal(button.closest('.modal')); //get parent of the .close
    });
});

function closeModal(modal) {
    modal.classList.add("hidden");
    modal.classList.remove("block");
}
