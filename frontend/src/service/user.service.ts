import http from "../common/http";
import IUserData from "../types/user.type";
const userEndpoint: string = "/users";

class UserDataService {
    getAll() {
        return http.get<Array<IUserData>>("/users");
    }

    get(id: string) {
        return http.get<IUserData>(`${userEndpoint}/${id}`);
    }

    create(data: IUserData) {
        return http.post<IUserData>(`${userEndpoint}`, data);
    }

    update(data: IUserData, id: any) {
        return http.put<any>(`${userEndpoint}/${id}`, data);
    }

    delete(id: any) {
        return http.delete<any>(`${userEndpoint}/${id}`);
    }

    deleteAll() {
        return http.delete<any>(`${userEndpoint}`);
    }

    findByUsername(title: string) {
        return http.get<Array<IUserData>>(`${userEndpoint}?username=${title}`);
    }
}

export default new UserDataService();
