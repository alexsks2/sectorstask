import axios from "axios";

const API_URL = 'http://localhost:8080/api/form';

class FormService {

    saveForm(username, sectorId, agreeToTerms) {
        return axios.post(API_URL, {
            username,
            sectorId,
            agreeToTerms: agreeToTerms
        })
    }

    updateForm(id, username, sectorId, agreeToTerms) {
        return axios.put(API_URL + '/' + id, {
            username,
            sectorId,
            agreeToTerms
        })
    }
}

export default new FormService;

