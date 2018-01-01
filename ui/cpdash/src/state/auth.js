export default function isUserAuthenticated() {
  // TODO: add logic to test if the user is connected
  return window.localStorage.getItem('_token') !== 'undefined' && window.localStorage.getItem('_token') !== null
}
