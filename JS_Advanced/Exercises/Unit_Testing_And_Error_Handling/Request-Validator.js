function validate(obj) {
  const validMethods = ["GET", "POST", "DELETE", "CONNECT"];
  const uriRegExp = /^[a-zA-Z.0-9*]+$/;
  const validVersion = ["HTTP/0.9", "HTTP/1.0", "HTTP/1.1", "HTTP/2.0"];
  const messageRegExp = /^[^\\<>\,\&\'"]+$/;

  if (!validMethods.includes(obj.method) || obj.method == undefined) {
    throw new Error("Invalid request header: Invalid Method");
  }

  if (!uriRegExp.test(obj.uri) || obj.uri == undefined) {
    throw new Error("Invalid request header: Invalid URI");
  }

  if (!validVersion.includes(obj.version) || obj.version == undefined) {
    throw new Error("Invalid request header: Invalid Version");
  }

  if (
    (!messageRegExp.test(obj.message) && obj.message.isEmpty) ||
    obj.message == undefined
  ) {
    throw new Error("Invalid request header: Invalid Message");
  }

  return obj;
}

validate({
  method: "POST",
  version: "HTTP/2.0",
  message: "rm -rf /*",
  uri: "*",
});
