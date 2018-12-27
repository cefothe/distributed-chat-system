from flask import Flask, request
from flask_restful import Resource, Api
from flask_jsonpify import jsonify
from numpy import unicode
from snips_nlu import SnipsNLUEngine

app = Flask(__name__)
api = Api(app)
engine = SnipsNLUEngine.from_path("train")

class NLU(Resource):

    def post(self):
        print(request.json)
        content = request.json['content']
        parsing = engine.parse(unicode(content))
        return jsonify(parsing)


api.add_resource(NLU, '/analyze')

if __name__ == '__main__':
    app.run(port='5002')
