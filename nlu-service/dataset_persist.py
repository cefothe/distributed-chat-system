import io
import json

with io.open("dataset.json") as f:
    sample_dataset = json.load(f)

from snips_nlu import load_resources, SnipsNLUEngine

load_resources(u"en")
nlu_engine = SnipsNLUEngine()

nlu_engine.fit(sample_dataset)
nlu_engine.persist("train")