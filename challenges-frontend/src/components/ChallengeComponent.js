import * as React from "react";
import ApiClient from "../ApiClient";

class ChallengeComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            a: '',
            b: '',
            guess: 0,
            user: '',
            message: ''
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
                        });
                } else {
                    this.updateMessage("Error: server error or not available");
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
            <div>
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
            </div>
        );
    }
}

export default ChallengeComponent;

