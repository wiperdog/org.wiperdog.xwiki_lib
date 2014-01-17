
def scriptFile = getClass().protectionDomain.codeSource.location.path
def scriptDir = new File(scriptFile).parent

def mongod = '/usr/bin/mongod'

def pidfilepath = " --pidfilepath " + scriptDir + '/run/mongod.pid'
def logpath =  " --logpath " + scriptDir + '/log/mongod.log'
def dbpath = " --dbpath " + scriptDir + '/data'
def fork = " --fork "
def otherargs = Args.serverargs(args)

def startupcmd = mongod + pidfilepath  + dbpath + logpath + fork + otherargs

println startupcmd

def proc = startupcmd.execute()
proc.waitFor()

println proc.text
