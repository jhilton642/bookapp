document.getElementById('book-search').addEventListener('click', getBooks);

function getBooks(ev) {
    let bookTable = document.getElementById('row');
    let searchTerm = document.getElementById('searchTerm');
    if (searchTerm.value.length === 0) {
        bookTable.innerHTML = 'Please Enter Something';
        return;
    }
    let url = `https://www.googleapis.com/books/v1/volumes?q=${searchTerm.value}&key=AIzaSyDk1o-UXnYwqsdpUdIC4RAZIg94eVUKHyA`;
    console.log(url);
    fetch(url)
        .then(response => response.json())
        .then(items => {
            let innerHTML = "";
            let row = 0;
            for (let book of items.items) {
                if (book.volumeInfo.imageLinks == undefined) {
                    book.volumeInfo['imageLinks'] = {'thumbnail': ''};
                }
                if (book.volumeInfo.pageCount == undefined) book.volumeInfo.pageCount = 0;
                if (book.volumeInfo.authors == undefined) book.volumeInfo.authors = "";
                row++;
                innerHTML +=
                    `<tr>
                        <td><img src="${book.volumeInfo.imageLinks.thumbnail}"></td>
                        <td>${book.volumeInfo.title}</td>
                        <td>${book.volumeInfo.authors}</td>
                        <td>${book.volumeInfo.pageCount}</td>
                        <td><button><a href = "${book.volumeInfo.infoLink}">Info</a></button></td>
                        <td>
                            <div class="dropdown">
                                <button class="dropbtn">Add to a list</button>
                                <div class="dropdown-content">
                                    <button id="${book.id}" onclick="addToNonFiction('${book.id}', '${book.volumeInfo.title}', '${book.volumeInfo.authors}', ${book.volumeInfo.pageCount} )">Non-Fiction</button>
                                    <button id="${book.id}" onclick="addToFantasy('${book.id}', '${book.volumeInfo.title}', '${book.volumeInfo.authors}', ${book.volumeInfo.pageCount} )">Fantasy</button>
                                    <button id="${book.id}" onclick="addToScienceFiction('${book.id}', '${book.volumeInfo.title}', '${book.volumeInfo.authors}', ${book.volumeInfo.pageCount} )">Science Fiction</button>
                                    <button id="${book.id}" onclick="addToMystery('${book.id}', '${book.volumeInfo.title}', '${book.volumeInfo.authors}', ${book.volumeInfo.pageCount} )">Mystery</button>
                                    <button id="${book.id}" onclick="addToPoetry('${book.id}', '${book.volumeInfo.title}', '${book.volumeInfo.authors}', ${book.volumeInfo.pageCount} )">Poetry</button>
                                    <button id="${book.id}" onclick="addToDrama('${book.id}', '${book.volumeInfo.title}', '${book.volumeInfo.authors}', ${book.volumeInfo.pageCount} )">Drama</button>
                                </div>
                            </div>
                        </td>
                    </tr>`;
            }
            bookTable.innerHTML = innerHTML;
        });
    ev.preventDefault();
}

function addToNonFiction(bookId, title, author, pageCount) {
    let url = `/booksAPI/saveToList`;
    let formData = new FormData();
    formData.append("bookId", bookId);
    formData.append("listName", 'non-fiction');
    formData.append("title", title);
    formData.append('author', author);
    formData.append('pageCount', pageCount);

    let request = {
        method: "POST",
        body: formData,
    };

    fetch(url, request)
        .then(response => response.json())
        .then(bookList => {
            let list = document.getElementById(bookId);
            list.innerText = "Added to " + bookList.listName;
        });
}

function addToFantasy(bookId, title, author, pageCount) {
    let url = `/booksAPI/saveToList`;
    let formData = new FormData();
    formData.append("bookId", bookId);
    formData.append("listName", 'fantasy');
    formData.append("title", title);
    formData.append('author', author);
    formData.append('pageCount', pageCount);

    let request = {
        method: "POST",
        body: formData,
    };

    fetch(url, request)
        .then(response => response.json())
        .then(bookList => {
            let list = document.getElementById(bookId);
            list.innerText = "Added to " + bookList.listName;
        });
}

function addToScienceFiction(bookId, title, author, pageCount) {
    let url = `/booksAPI/saveToList`;
    let formData = new FormData();
    formData.append("bookId", bookId);
    formData.append("listName", 'science-fiction');
    formData.append("title", title);
    formData.append('author', author);
    formData.append('pageCount', pageCount);

    let request = {
        method: "POST",
        body: formData,
    };

    fetch(url, request)
        .then(response => response.json())
        .then(bookList => {
            let list = document.getElementById(bookId);
            list.innerText = "Added to " + bookList.listName;
        });
}

function addToMystery(bookId, title, author, pageCount) {
    let url = `/booksAPI/saveToList`;
    let formData = new FormData();
    formData.append("bookId", bookId);
    formData.append("listName", 'mystery');
    formData.append("title", title);
    formData.append('author', author);
    formData.append('pageCount', pageCount);

    let request = {
        method: "POST",
        body: formData,
    };

    fetch(url, request)
        .then(response => response.json())
        .then(bookList => {
            let list = document.getElementById(bookId);
            list.innerText = "Added to " + bookList.listName;
        });
}

function addToPoetry(bookId, title, author, pageCount) {
    let url = `/booksAPI/saveToList`;
    let formData = new FormData();
    formData.append("bookId", bookId);
    formData.append("listName", 'poetry');
    formData.append("title", title);
    formData.append('author', author);
    formData.append('pageCount', pageCount);

    let request = {
        method: "POST",
        body: formData,
    };

    fetch(url, request)
        .then(response => response.json())
        .then(bookList => {
            let list = document.getElementById(bookId);
            list.innerText = "Added to " + bookList.listName;
        });
}

function addToDrama(bookId, title, author, pageCount) {
    let url = `/booksAPI/saveToList`;
    let formData = new FormData();
    formData.append("bookId", bookId);
    formData.append("listName", 'drama');
    formData.append("title", title);
    formData.append('author', author);
    formData.append('pageCount', pageCount);

    let request = {
        method: "POST",
        body: formData,
    };

    fetch(url, request)
        .then(response => response.json())
        .then(bookList => {
            let list = document.getElementById(bookId);
            list.innerText = "Added to " + bookList.listName;
        });
}