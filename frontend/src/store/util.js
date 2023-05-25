// 새로고침해도 상태를 영속시키기 위해 상태를 일괄 LocalStorage에 저장.
const KEY_STATE = 'reduxState'
const initState = {};

export function loadStates() {
    return JSON.parse(localStorage.getItem(KEY_STATE)) || initState;
}

export function saveStates(state) {
    localStorage.setItem(KEY_STATE, JSON.stringify(state));
}
