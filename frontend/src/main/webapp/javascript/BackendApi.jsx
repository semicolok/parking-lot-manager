const BACKEND_BASE_URL = 'http://localhost:8080';

let instance = null;

class BackendApi {
    constructor() {
        if(!instance) {
            instance = this;
        }
        return instance;
    }

    search(queryParameters) {
        return exchange("GET", "/search?" + queryParameters);
    }
}


const exchange = (method, url, headers, payload) => {
    return fetch(BACKEND_BASE_URL + url, {
        method: method,
        headers: headers,
        payload: payload,
        credentials: "include",
        disableRedirects: false,
        timeout: 10 * 1000,
        body: payload
    }).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error(response.json());
        }
    })
};

export default new BackendApi();
