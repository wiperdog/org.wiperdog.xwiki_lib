import static User.*
println username

String [] testargs = [ "--port", "2800" ]

println Args.serverargs(testargs)
println Args.clientargs(testargs)

testargs = (String [] ) [ "--auth"  ]
println Args.serverargs(testargs)
println Args.clientargs(testargs)

// println Args.serverargs(args)
// println Args.clientargs(args)

