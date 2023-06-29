import * as React from "react";
import ApiClient from "../services/ApiClient";
import LastAttemptsComponent from "./LastAttemptsComponent";

class ChallengeComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            a: '',
            b: '',
            guess: 0,
            user: '',
            message: '',
            lastAttempts: [],
        }
        this.handleSubmitResult = this.handleSubmitResult.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() : void {
        ApiClient.challenge().then(
            res => {
                if (res.ok) {
                    res.json().then(
                        json => {
                            this.setState({
                                a: json.factorA,
                                b: json.factorB
                            });
                        }
                    );
                } else {
                    this.updateMessage("Can't reach the server");
                }
            }
        );
    }

    handleChange(event) {
        const name = event.target.name;
        this.setState({
            [name]: event.target.value
        });
    }

    refreshChallenge() {
        ApiClient.challenge().then(res => {
           if (res.ok) {
                res.json().then(json => this.setState({
                    a: json.factorA,
                    b: json.factorB
                }));
           } else {
               this.updateMessage("can't reach the Server");
           }
        });
    }

    handleSubmitResult(event) {
        event.preventDefault();
        ApiClient.sendGuess(
            this.state.user,
            this.state.a,
            this.state.b,
            this.state.guess
        ).then(
            res => {
                if (res.ok) {
                    res.json().then(
                        json => {
                            if (json.correct) {
                                this.updateMessage("Congratulations! Your guess is correct");
                            } else {
                                this.updateMessage("Oops! Your guess " + json.resultAttempt +
                                    " is wrong, but keep playing!");
                            }
                            this.updateLastAttempts(this.state.user);
                            this.refreshChallenge();
                        });
                } else {
                    this.updateMessage("Error: server error or not available");
                }
            });
    }

    updateLastAttempts(userAlias: string) {
        ApiClient.getAttempts(userAlias).then(res => {
            if (res.ok) {
                let attempts: Attempt[] = [];
                res.json().then(data => {
                    data.forEach(item => attempts.push(item));
                    this.setState({lastAttempts: attempts});
                });
            } else {

            }
        });
    }

    updateMessage(m: string) {
        this.setState({
            message: m
        });
    }

    render() {
        return (
            <div className="display-column">
                <div>
                    <h3>Your new challenge is</h3>
                    <h1>{this.state.a} x {this.state.b}</h1>
                </div>
                <form onSubmit={this.handleSubmitResult}>
                    <label>
                        Your alias:
                        <input type="text"
                               name="user"
                               maxLength="12"
                               onChange={this.handleChange}
                               value={this.state.user}
                        />
                    </label>
                    <br />
                    <label>
                        Your guess:
                        <input type="number"
                               name="guess"
                               min="0"
                               onChange={this.handleChange}
                               value={this.state.guess}
                        />
                    </label>
                    <br />
                    <input type="submit" value="Submit" />
                </form>
                <h4>{this.state.message}</h4>
                {this.state.lastAttempts.length > 0 &&
                    <LastAttemptsComponent lastAttempts={this.state.lastAttempts} />
                }
            </div>
        );
    }
}

export default ChallengeComponent;

