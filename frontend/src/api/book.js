import axios from 'axios'

export function getBooks() {
  return axios.get('/books')
}

export function getBookById(id) {
  return axios.get(`/books/${id}`)
}

export function addBook(book) {
  return axios.post('/books', book)
}

export function updateBook(book) {
  return axios.put('/books', book)
}

export function deleteBook(id) {
  return axios.delete(`/books/${id}`)
} 