const { execSync } = require("child_process");
const path = require("path");
const os = require("os");

const isWindows = os.platform() === "win32";
const gradlewCmd = isWindows ? "gradlew.bat" : "./gradlew";

const androidPath = path.join(__dirname, "..", "android");

try {
    execSync(`${gradlewCmd} clean build test`, { cwd: androidPath, stdio: "inherit", shell: true });
} catch (error) {
    process.exit(1);
}
