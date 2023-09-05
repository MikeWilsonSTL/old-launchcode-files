function validateInput(input: string): "Empty" | "Not a Number" | "Is a Number" {
    if (input === "") {
        return "Empty";
    }
    else if (typeof (input) === "string") {
        return "Not a Number";
    }
    else {
        return "Is a Number";
    }
}

function formSubmission(document, pilot: string, coPilot: string, fuelLevel: string, cargoMass: string) {
    if (true /*fuelLevel > 10000 && cargoMass < 10000*/) {
        let launchStatus = `Shuttle is Ready for Launch`;
        let launchStatusColor = `green`;
        let firstPart = `<li>Pilot ${pilot} is ready to launch</li>`;
        let secondPart = `<li>Co-pilot ${coPilot} is ready to launch</li>`;
        let thirdPart = `<li>Fuel level high enough for launch</li>`; // input <= 10,000
        let fourthPart = `<li>Cargo mass low enough for launch</li>`; // input <= 10,000
    }
    else {
        let launchStatus = `Shuttle Not Ready for Launch`;
        let launchStatusColor = `red`;
        let firstPart = `<li>Pilot ${pilot} is ready to launch</li>`;
        let secondPart = `<li>Co-pilot ${coPilot} is ready to launch</li>`;
        let thirdPart = `<li>Fuel level too low for launch</li>`;
        let fourthPart = `<li>Cargo mass too heavy for launch</li>`;
    }
}