import { ChangeEvent, Component } from "react";
import TutorialDataService from "../service/user.service";
import IUserData from "../types/user.type";

type Props = {};

type State = IUserData & {
    submitted: boolean
};

export default class AddUser extends Component<Props, State> {
    constructor(props: Props) {
        super(props);
        this.onChangeUsername = this.onChangeUsername.bind(this);
        this.onChangeEmail = this.onChangeEmail.bind(this);
        this.saveUser = this.saveUser.bind(this);
        this.newUser = this.newUser.bind(this);

        this.state = {
            id: null,
            username: "",
            email: "",
            passwordHash: "",
            submitted: false
        };
    }

    onChangeUsername(e: ChangeEvent<HTMLInputElement>) {
        this.setState({
            username: e.target.value
        });
    }

    onChangeEmail(e: ChangeEvent<HTMLInputElement>) {
        this.setState({
            email: e.target.value
        });
    }

    saveUser() {
        const data: IUserData = {
            username: this.state.username,
            email: this.state.email,
            passwordHash: this.state.passwordHash,
        };

        TutorialDataService.create(data)
            .then((response: any) => {
                this.setState({
                    id: response.data.id,
                    username: this.state.username,
                    email: this.state.email,
                    passwordHash: this.state.passwordHash,
                    submitted: true
                });
                console.log(response.data);
            })
            .catch((e: Error) => {
                console.log(e);
            });
    }

    newUser() {
        this.setState({
            id: null,
            username: "",
            email: "",
            passwordHash: "",
            submitted: false
        });
    }

    // render() {
    //     //...
    // }
}
