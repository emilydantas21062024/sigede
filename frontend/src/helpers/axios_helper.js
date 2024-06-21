import axios from 'axios';

axios.defaults.headers.common = {
    'X-Requested-With': null,
};
export const getAuthToken = () => {
    return window.localStorage.getItem('auth_token');
};

export const setAuthHeader = (token) => {
    if (token !== null) {
        window.localStorage.setItem("auth_token", token);
    } else {
        window.localStorage.removeItem("auth_token");
    }
};

axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.post['Content-Type'] = 'application/json';

export const request = (method, url, data) => {
    let headers = {};
    headers['Accept'] = 'application/json';
    headers['Content-Type'] = 'application/json';

    if (getAuthToken() !== null && getAuthToken() !== "null") {
        headers['Authorization'] = `Bearer ${getAuthToken()}`;
    }

    return axios({
        method: method,
        url: url,
        data: data,
        headers: headers
    });
    // return fetch(url, {
    //     method: method,
    //     headers: headers,
    //     body: JSON.stringify(data)
    // });
};

export const updateItemValue = (event, item,setItem) => {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    setItem({...item, [name]: value});
};
